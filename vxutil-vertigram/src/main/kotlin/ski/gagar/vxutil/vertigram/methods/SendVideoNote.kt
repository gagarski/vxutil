package ski.gagar.vxutil.vertigram.methods

import ski.gagar.vertigram.annotations.TgMethod
import ski.gagar.vxutil.vertigram.throttling.HasChatId
import ski.gagar.vxutil.vertigram.throttling.Throttled
import ski.gagar.vxutil.vertigram.types.ChatId
import ski.gagar.vxutil.vertigram.types.Message
import ski.gagar.vxutil.vertigram.types.ReplyMarkup
import ski.gagar.vxutil.vertigram.types.ReplyParameters
import ski.gagar.vxutil.vertigram.types.attachments.Attachment
import ski.gagar.vxutil.vertigram.util.multipart.TgMedia
import java.time.Duration

@TgMethod
@Throttled
data class SendVideoNote(
    override val chatId: ChatId,
    @TgMedia
    val videoNote: Attachment,
    val duration: Duration? = null,
    val length: Int? = null,
    @TgMedia
    val thumbnail: Attachment? = null,
    val disableNotification: Boolean = false,
    val protectContent: Boolean = false,
    val replyMarkup: ReplyMarkup? = null,
    // Since Telegram Bot Api 6.3
    val messageThreadId: Long? = null,
    // Since Telegram Bot API 7.0
    val replyParameters: ReplyParameters? = null
) : MultipartTgCallable<Message>(), HasChatId
