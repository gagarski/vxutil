package ski.gagar.vxutil.vertigram.methods

import ski.gagar.vertigram.annotations.TgMethod
import ski.gagar.vxutil.vertigram.throttling.HasChatId
import ski.gagar.vxutil.vertigram.throttling.Throttled
import ski.gagar.vxutil.vertigram.types.ChatId
import ski.gagar.vxutil.vertigram.types.InlineKeyboardMarkup
import ski.gagar.vxutil.vertigram.types.InputMedia
import ski.gagar.vxutil.vertigram.types.Message
import ski.gagar.vxutil.vertigram.util.multipart.TgMedia

@TgMethod
@Throttled
data class EditMessageMedia(
    @TgMedia
    val media: InputMedia,
    override val chatId: ChatId? = null,
    val messageId: Long? = null,
    val inlineMessageId: Long? = null,
    val replyMarkup: InlineKeyboardMarkup? = null
) : MultipartTgCallable<Message>(), HasChatId
