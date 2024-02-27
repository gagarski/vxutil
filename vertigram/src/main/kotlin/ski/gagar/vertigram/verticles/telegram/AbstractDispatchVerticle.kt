package ski.gagar.vertigram.verticles.telegram

import kotlinx.coroutines.launch
import ski.gagar.vertigram.telegram.client.Telegram
import ski.gagar.vertigram.telegram.client.ThinTelegram
import ski.gagar.vertigram.telegram.markup.toRichText
import ski.gagar.vertigram.telegram.methods.sendMessage
import ski.gagar.vertigram.telegram.types.Message
import ski.gagar.vertigram.telegram.types.Update
import ski.gagar.vertigram.telegram.types.util.toChatId
import ski.gagar.vertigram.verticles.common.AbstractHierarchyVerticle
import ski.gagar.vertigram.verticles.common.messages.DeathNotice
import ski.gagar.vertigram.verticles.common.messages.DeathReason
import ski.gagar.vertigram.verticles.telegram.address.TelegramAddress

abstract class AbstractDispatchVerticle<Config : AbstractDispatchVerticle.Config, DialogKey> : AbstractHierarchyVerticle<Config>() {
    open val tgVAddressBase = TelegramAddress.TELEGRAM_VERTICLE_BASE
    protected val tg: Telegram by lazy {
        ThinTelegram(vertigram, tgVAddressBase)
    }


    protected val dialogs = mutableMapOf<DialogKey, DialogDescriptor>()
    protected val dialogsInv = mutableMapOf<String, DialogKey>()

    protected abstract fun dialogKey(msg: Message): DialogKey?
    protected abstract fun dialogKey(q: Update.CallbackQuery.Payload): DialogKey?
    protected abstract fun toChatId(key: DialogKey): Long?
    protected abstract suspend fun shouldHandleCallbackQuery(q: Update.CallbackQuery.Payload): Boolean
    protected abstract suspend fun shouldHandleMessage(msg: Message): Boolean
    protected abstract suspend fun doStart(dialogKey: DialogKey, msg: Message): DialogDescriptor?

    override suspend fun start() {
        super.start()
        consumer<Message, Unit>(TelegramAddress.demuxAddress(Update.Type.MESSAGE, typedConfig.baseAddress)) {
            handleMessage(it)
        }

        consumer<Update.CallbackQuery.Payload, Unit>(TelegramAddress.demuxAddress(Update.Type.CALLBACK_QUERY, typedConfig.baseAddress)) {
            handleCallbackQuery(it)
        }
    }

    private suspend fun handleCallbackQuery(callbackQuery: Update.CallbackQuery.Payload) {
        val key = dialogKey(callbackQuery) ?: return
        val child = dialogs[key] ?: return

        if (!shouldHandleCallbackQuery(callbackQuery))
            return

        passCallbackQueryToOngoing(callbackQuery, child)
    }

    private suspend fun handleMessage(message: Message) {
        if (!shouldHandleMessage(message))
            return

        val dialogKey = dialogKey(message) ?: return
        val ongoing = dialogs[dialogKey] // after suspending
        if (ongoing == null) {
            startAndInitialize(dialogKey, message)
        } else {
            passMessageToOngoing(message, dialogs[dialogKey]!!)
        }
    }

    private suspend fun startAndInitialize(dialogKey: DialogKey, msg: Message) {
        val desc = doStart(dialogKey, msg) ?: return
        dialogs[dialogKey] = desc
        dialogsInv[desc.id] = dialogKey
    }

    private suspend fun passMessageToOngoing(message: Message, desc: DialogDescriptor) {
        vertigram.eventBus.send(
            desc.messageAddress,
            message
        )
    }

    private suspend fun passCallbackQueryToOngoing(callbackQuery: Update.CallbackQuery.Payload, desc: DialogDescriptor) {
        vertigram.eventBus.send(
            desc.callbackQueryAddress,
            callbackQuery
        )
    }

    override fun onChildDeath(deathNotice: DeathNotice) {
        val key = dialogsInv[deathNotice.id] ?: return
        val chatId = toChatId(key)

        if (deathNotice.reason == DeathReason.FAILED && null != chatId) {
            launch {
                tg.sendMessage(
                    richText = "Something went wrong".toRichText(),
                    chatId = chatId.toChatId(),
                )
            }
        }

        dialogsInv.remove(deathNotice.id)
        dialogs.remove(key)
    }

    data class DialogDescriptor(val id: String, val messageAddress: String, val callbackQueryAddress: String)

    interface Config {
        val baseAddress: String
    }
}
