package ski.gagar.vxutil.vertigram.types

/**
 * Telegram type InlineQueryResultDocument.
 */
data class InlineQueryResultDocument(
    val id: String,
    val title: String,
    val caption: String? = null,
    val parseMode: ParseMode? = null,
    val captionEntities: List<MessageEntity>? = null,
    val documentUrl: String,
    val mimeType: String,
    val description: String? = null,
    val replyMarkup: InlineKeyboardMarkup? = null,
    val inputMessageContent: InputMessageContent? = null,
    val thumbUrl: String? = null,
    val thumbWidth: Long? = null,
    val thumbHeight: Long? = null
) : InlineQueryResult() {
    override val type: InlineQueryResultType = InlineQueryResultType.DOCUMENT
}

val InlineQueryResultDocument.captionEntitiesInstantiated: List<InstantiatedEntity>
    get() = captionEntities?.map { InstantiatedEntity(it, this.caption) } ?: listOf()
