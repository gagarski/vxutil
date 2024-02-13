package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.annotations.TelegramCodegen
import ski.gagar.vertigram.annotations.TelegramMedia
import ski.gagar.vertigram.annotations.TelegramMethod
import ski.gagar.vertigram.throttling.HasChatId
import ski.gagar.vertigram.throttling.Throttled
import ski.gagar.vertigram.types.InputMedia
import ski.gagar.vertigram.types.Message
import ski.gagar.vertigram.types.ReplyMarkup
import ski.gagar.vertigram.types.util.ChatId
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [editMessageMedia](https://core.telegram.org/bots/api#editmessagemedia) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
sealed interface EditMessageMedia {
    /**
     * Inline message case
     */
    @TelegramMethod(
        methodName = "editMessageMedia"
    )
    @TelegramCodegen(
        methodName = "editMessageMedia",
        generatePseudoConstructor = true,
        pseudoConstructorName = "EditMessageMedia"
    )
    @Throttled
    data class InlineMessage internal constructor(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val inlineMessageId: Long,
        @TelegramMedia
        val media: InputMedia,
        val replyMarkup: ReplyMarkup.InlineKeyboard? = null
    ) : EditMessageMedia, MultipartTelegramCallable<Boolean>()

    /**
     * Chat message case
     */
    @TelegramMethod(
        methodName = "editMessageMedia"
    )
    @TelegramCodegen(
        methodName = "editMessageMedia",
        generatePseudoConstructor = true,
        pseudoConstructorName = "EditMessageMedia"
    )
    @Throttled
    data class ChatMessage internal constructor(
        override val chatId: ChatId,
        val messageId: Long,
        @TelegramMedia
        val media: InputMedia,
        val replyMarkup: ReplyMarkup.InlineKeyboard? = null
    ) : EditMessageMedia, HasChatId, MultipartTelegramCallable<Message>()
}