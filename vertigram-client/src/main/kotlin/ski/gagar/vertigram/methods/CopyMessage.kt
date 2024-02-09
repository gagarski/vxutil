package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonUnwrapped
import ski.gagar.vertigram.annotations.TgMethod
import ski.gagar.vertigram.richtext.RichCaption
import ski.gagar.vertigram.throttling.HasChatId
import ski.gagar.vertigram.throttling.Throttled
import ski.gagar.vertigram.types.*
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [copyMessage](https://core.telegram.org/bots/api#copymessage) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
@TgMethod
@Throttled
data class CopyMessage(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    override val chatId: ChatId,
    val messageThreadId: Long? = null,
    val fromChatId: ChatId,
    val messageId: Long,
    val caption: String? = null,
    val parseMode: ParseMode? = null,
    val captionEntities: List<MessageEntity>? = null,
    val disableNotification: Boolean = false,
    val protectContent: Boolean = false,
    val replyParameters: ReplyParameters? = null,
    val replyMarkup: ReplyMarkup? = null
) : JsonTgCallable<Message>(), HasChatId
