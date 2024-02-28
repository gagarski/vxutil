package ski.gagar.vertigram.telegram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ski.gagar.vertigram.annotations.TelegramCodegen
import ski.gagar.vertigram.telegram.annotations.TelegramMethod
import ski.gagar.vertigram.telegram.throttling.HasChatId
import ski.gagar.vertigram.telegram.throttling.Throttled
import ski.gagar.vertigram.telegram.types.InputMedia
import ski.gagar.vertigram.telegram.types.Message
import ski.gagar.vertigram.telegram.types.ReplyMarkup
import ski.gagar.vertigram.telegram.types.util.ChatId
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [editMessageMedia](https://core.telegram.org/bots/api#editmessagemedia) method.
 *
 * Subtypes (which are nested) are two mutually-exclusive cases: for inline message and for chat message.
 * Note the different return types in these cases.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes(
    JsonSubTypes.Type(EditMessageMedia.InlineMessage::class),
    JsonSubTypes.Type(EditMessageMedia.ChatMessage::class)
)
sealed interface EditMessageMedia {
    val media: InputMedia
    val replyMarkup: ReplyMarkup.InlineKeyboard?
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
        val inlineMessageId: String,
        @ski.gagar.vertigram.telegram.annotations.TelegramMedia
        override val media: InputMedia,
        override val replyMarkup: ReplyMarkup.InlineKeyboard? = null
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
        @ski.gagar.vertigram.telegram.annotations.TelegramMedia
        override val media: InputMedia,
        override val replyMarkup: ReplyMarkup.InlineKeyboard? = null
    ) : EditMessageMedia, HasChatId, MultipartTelegramCallable<Message>()
}