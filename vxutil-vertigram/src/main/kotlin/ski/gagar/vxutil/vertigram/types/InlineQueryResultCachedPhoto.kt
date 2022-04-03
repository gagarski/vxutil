package ski.gagar.vxutil.vertigram.types

/**
 * Telegram type InlineQueryResultCachedPhoto.
 */
data class InlineQueryResultCachedPhoto(
    val id: String,
    val photoFileId: String,
    val title: String? = null,
    val description: String? = null,
    val caption: String? = null,
    val parseMode: ParseMode? = null,
    val captionEntities: List<MessageEntity>? = null,
    val replyMarkup: InlineKeyboardMarkup? = null,
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult() {
    override val type: InlineQueryResultType = InlineQueryResultType.PHOTO
}

val InlineQueryResultCachedPhoto.captionEntitiesInstantiated: List<InstantiatedEntity>
    get() = captionEntities?.map { InstantiatedEntity(it, this.caption) } ?: listOf()
