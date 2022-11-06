package ski.gagar.vxutil.vertigram.verticles

import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import kotlinx.coroutines.delay
import ski.gagar.vxutil.ErrorLoggingCoroutineVerticle
import ski.gagar.vxutil.jackson.mapTo
import ski.gagar.vxutil.jackson.publishJson
import ski.gagar.vxutil.lazy
import ski.gagar.vxutil.logger
import ski.gagar.vxutil.retrying
import ski.gagar.vxutil.vertigram.client.Telegram
import ski.gagar.vxutil.vertigram.client.TgVTelegram
import ski.gagar.vxutil.vertigram.config.WebHookConfig
import ski.gagar.vxutil.vertigram.deleteWebhook
import ski.gagar.vxutil.vertigram.setWebhook
import ski.gagar.vxutil.vertigram.types.ParsedUpdate
import ski.gagar.vxutil.vertigram.types.ParsedUpdateList
import ski.gagar.vxutil.vertigram.types.UpdateType
import ski.gagar.vxutil.vertigram.util.json.TELEGRAM_JSON_MAPPER
import ski.gagar.vxutil.web.IpNetworkAddress
import ski.gagar.vxutil.web.RealIpLoggerHandler
import java.util.*

class WebHook : ErrorLoggingCoroutineVerticle() {
    private val secret = UUID.randomUUID()
    private val typedConfig by lazy {
        config.mapTo<Config>()
    }
    private val tg: Telegram by lazy {
        TgVTelegram(vertx, typedConfig.tgvAddress)
    }

    override suspend fun start() {
        logger.lazy.info { "Deleting old webhook..." }
        retrying(coolDown = { delay(3000) }) {
            tg.deleteWebhook()
        }

        logger.lazy.info { "Staring $javaClass server..." }
        val server = vertx.createHttpServer()
        val router = Router.router(vertx)
        router.route().handler(RealIpLoggerHandler(
            trustedNetworks = typedConfig.webHook.proxy?.trustedNetworks?.map { IpNetworkAddress(it) }?.toSet() ?: setOf(),
            trustDomainSockets = typedConfig.webHook.proxy?.trustDomainSockets ?: false))
        router.route().handler(BodyHandler.create())

        val addr =
            if (typedConfig.webHook.base.startsWith("/")) typedConfig.webHook.base else "/${typedConfig.webHook.base}"

        router.post(addr).handler { context ->
            if (context.request().getHeader(X_TELEGRAM_BOT_API_SECRET_TOKEN) != secret.toString()) {
                context.response().statusCode = HttpResponseStatus.FORBIDDEN.code()
                context.response().end()
                return@handler
            }
            val json = context.body().asJsonObject()
            val req = try {
                json.mapTo(
                    ParsedUpdate::class.java,
                    TELEGRAM_JSON_MAPPER
                )
            } catch (ex: Exception) {
                logger.lazy.error(throwable = ex) { "Malformed update from Telegram $json, skipping it" }
                // It's ugly to send successful response back to Telegram.
                // But otherwise (either when returning 40x or 50x codes) Telegram will retry these requests
                // First, it's unclear when will it give up (docs say "after a reasonable amount of attempts")
                // Second, returning 400 or 500 blocks other updates (at least from same chat) unless Telegram gives up,
                // or we return 200.
                context.response().end()
                return@handler
            }
            logger.lazy.trace { "Received update $req" }
            logger.lazy.trace { "Publishing $req" }
            vertx.eventBus().publishJson(typedConfig.updatePublishingAddress, ParsedUpdateList(listOf(req)))
            context.response().end()
        }

        server.requestHandler(router)
        server.listen(typedConfig.webHook.port, typedConfig.webHook.host)

        logger.lazy.info { "Setting new Telegram webhook..." }
        retrying(coolDown = { delay(3000) }) {
            tg.setWebhook(
                typedConfig.webHook.publicUrl,
                allowedUpdates = typedConfig.allowedUpdates,
                secretToken = secret.toString()
            )
        }

        logger.lazy.info { "Web server is listening..." }
    }

    data class Config(
        val tgvAddress: String = TelegramVerticle.Config.DEFAULT_BASE_ADDRESS,
        val updatePublishingAddress: String = DEFAULT_UPDATE_PUBLISHING_ADDRESS,
        val webHook: WebHookConfig = WebHookConfig(),
        val allowedUpdates: List<UpdateType>? = null
    ) {
        companion object {
            const val DEFAULT_UPDATE_PUBLISHING_ADDRESS = "ski.gagar.vertigram.updates"
        }
    }

    companion object {
        private const val X_TELEGRAM_BOT_API_SECRET_TOKEN = "X-Telegram-Bot-Api-Secret-Token"
    }
}
