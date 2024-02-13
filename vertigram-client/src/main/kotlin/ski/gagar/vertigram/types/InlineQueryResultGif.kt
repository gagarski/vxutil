package ski.gagar.vertigram.types

import java.time.Duration

data class InlineQueryResultGif(
    val id: String,
    val gifUrl: String,
    val gifWidth: Int? = null,
    val gifHeight: Int? = null,
    val gifDuration: Duration? = null,
    val thumbnailUrl: String,
    val thumbnailMimeType: String? = null,
    val title: String? = null,
    val caption: String? = null,
    val parseMode: ParseMode? = null,
    val captionEntities: List<MessageEntity>? = null,
    val replyMarkup: ReplyMarkup.InlineKeyboard? = null,
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult {
    override val type: InlineQueryResultType = InlineQueryResultType.GIF
}
