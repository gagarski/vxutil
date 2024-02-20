package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.annotations.TelegramCodegen
import ski.gagar.vertigram.annotations.TelegramMethod
import ski.gagar.vertigram.types.Update
import ski.gagar.vertigram.util.NoPosArgs
import java.time.Duration

/**
 * Low-level representation of Telegram [getUpdates](https://core.telegram.org/bots/api#getupdates) method.
 *
 * Note that this is a low-level representation of the method used by [ski.gagar.vertigram.client.Telegram].
 * The return value is treated as a list of Map<String, Any?>, this is done to skip malformed updates:
 * for example after API update. Instead of trying to use this method directly,
 * use [ski.gagar.vertigram.client.Telegram.getUpdates] method, which gives you properly parsed updates and
 * abstracts you out of long-poll logic and timeout
 *
 * For up-to-date documentation for telegram method please consult the official Telegram docs.
 */
@Deprecated("Use Telegram.getUpdates instead")
@TelegramMethod(
    methodName = "getUpdates",
    generateVerticleConsumer = false
)
@TelegramCodegen(
    generateMethod = false
)
internal data class GetUpdatesRaw(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    val offset: Long? = null,
    val timeout: Duration = Duration.ZERO,
    val limit: Int? = null,
    val allowedUpdates: List<Update.Type>? = null
) : JsonTelegramCallable<List<Map<String, Any?>>>()
