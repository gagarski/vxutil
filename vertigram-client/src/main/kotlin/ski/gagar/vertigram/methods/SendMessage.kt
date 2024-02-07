package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.annotations.TgMethod
import ski.gagar.vertigram.throttling.HasChatId
import ski.gagar.vertigram.throttling.Throttled
import ski.gagar.vertigram.types.*
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [sendMessage](https://core.telegram.org/bots/api#sendmessage) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
@TgMethod
@Throttled
data class SendMessage(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    override val chatId: ChatId,
    val messageThreadId: Long? = null,
    val text: String,
    val parseMode: ParseMode? = null,
    val entities: List<MessageEntity>? = null,
    val linkPreviewOptions: LinkPreviewOptions? = null,
    val disableNotification: Boolean = false,
    val protectContent: Boolean = false,
    val replyParameters: ReplyParameters? = null,
    val replyMarkup: ReplyMarkup? = null
) : JsonTgCallable<Message>(), HasChatId
