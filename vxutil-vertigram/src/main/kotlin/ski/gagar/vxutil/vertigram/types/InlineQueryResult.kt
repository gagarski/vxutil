package ski.gagar.vxutil.vertigram.types

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

/**
 * Telegram type InlineQueryResult.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
// We use @class here because type field does not properly distinguish sub-types (cached vs non-cached.
@JsonSubTypes(
    JsonSubTypes.Type(value = InlineQueryResultArticle::class),
    JsonSubTypes.Type(value = InlineQueryResultPhoto::class),
    JsonSubTypes.Type(value = InlineQueryResultGif::class),
    JsonSubTypes.Type(value = InlineQueryResultMpeg4Gif::class),
    JsonSubTypes.Type(value = InlineQueryResultVideo::class),
    JsonSubTypes.Type(value = InlineQueryResultAudio::class),
    JsonSubTypes.Type(value = InlineQueryResultVoice::class),
    JsonSubTypes.Type(value = InlineQueryResultDocument::class),
    JsonSubTypes.Type(value = InlineQueryResultLocation::class),
    JsonSubTypes.Type(value = InlineQueryResultVenue::class),
    JsonSubTypes.Type(value = InlineQueryResultContact::class),
    JsonSubTypes.Type(value = InlineQueryResultGame::class),
    JsonSubTypes.Type(value = InlineQueryResultCachedPhoto::class),
    JsonSubTypes.Type(value = InlineQueryResultCachedGif::class),
    JsonSubTypes.Type(value = InlineQueryResultCachedMpeg4Gif::class),
    JsonSubTypes.Type(value = InlineQueryResultCachedSticker::class),
    JsonSubTypes.Type(value = InlineQueryResultCachedDocument::class),
    JsonSubTypes.Type(value = InlineQueryResultCachedVideo::class),
    JsonSubTypes.Type(value = InlineQueryResultCachedVoice::class),
    JsonSubTypes.Type(value = InlineQueryResultCachedAudio::class),
)
sealed class InlineQueryResult {
    abstract val type: InlineQueryResultType
}

/**
 * Telegram type InlineQueryResultArticle.
 */
data class InlineQueryResultArticle(
    val id: String,
    val title: String,
    val inputMessageContent: InputMessageContent,
    val replyMarkup: InlineKeyboardMarkup? = null,
    val url: String? = null,
    val hideUrl: Boolean = false,
    val description: String? = null,
    val thumbUrl: String? = null,
    val thumbWidth: Long? = null,
    val thumbHeight: Long? = null
) : InlineQueryResult() {
    override val type = InlineQueryResultType.ARTICLE
}
