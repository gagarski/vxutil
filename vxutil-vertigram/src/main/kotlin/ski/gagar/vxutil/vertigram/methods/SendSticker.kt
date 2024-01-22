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

@TgMethod
@Throttled
data class SendSticker(
    override val chatId: ChatId,
    @TgMedia
    val sticker: Attachment,
    val disableNotification: Boolean = false,
    val protectContent: Boolean = false,
    val replyMarkup: ReplyMarkup? = null,
    // Since Telegram Bot API 6.3
    val messageThreadId: Long? = null,
    // Since Telegram Bot API 6.6
    val emoji: String? = null,
    // Since Telegram Bot API 7.0
    val replyParameters: ReplyParameters? = null
) : MultipartTgCallable<Message>(), HasChatId
