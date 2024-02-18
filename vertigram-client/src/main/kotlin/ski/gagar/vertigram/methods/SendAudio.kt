package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.annotations.TelegramMedia
import ski.gagar.vertigram.throttling.HasChatId
import ski.gagar.vertigram.throttling.Throttled
import ski.gagar.vertigram.types.Message
import ski.gagar.vertigram.types.MessageEntity
import ski.gagar.vertigram.types.ParseMode
import ski.gagar.vertigram.types.ReplyMarkup
import ski.gagar.vertigram.types.ReplyParameters
import ski.gagar.vertigram.types.attachments.Attachment
import ski.gagar.vertigram.types.richtext.HasOptionalRichCaption
import ski.gagar.vertigram.types.util.ChatId
import ski.gagar.vertigram.util.NoPosArgs
import java.time.Duration

/**
 * Telegram [sendAudio](https://core.telegram.org/bots/api#sendaudio) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
@Throttled
data class SendAudio(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    override val chatId: ChatId,
    val messageThreadId: Long? = null,
    @TelegramMedia
    val audio: Attachment,
    override val caption: String? = null,
    override val parseMode: ParseMode? = null,
    override val captionEntities: List<MessageEntity>? = null,
    val duration: Duration? = null,
    val performer: String? = null,
    val title: String? = null,
    val thumbnail: Attachment? = null,
    val disableNotification: Boolean = false,
    val protectContent: Boolean = false,
    val replyParameters: ReplyParameters? = null,
    val replyMarkup: ReplyMarkup? = null
) : MultipartTelegramCallable<Message>(), HasChatId, HasOptionalRichCaption
