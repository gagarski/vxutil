package ski.gagar.vxutil.vertigram.types

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vxutil.vertigram.types.attachments.Attachment

/**
 * Telegram type InputMediaAnimation
 */
data class InputMediaAnimation(
    override val media: Attachment,
    override val thumb: Attachment? = null,
    val caption: String? = null,
    val parseMode: ParseMode? = null,
    val captionEntities: List<MessageEntity>? = null,
    val width: Long? = null,
    val height: Long? = null,
    val duration: Long? = null
) : InputMedia() {
    override val type: InputMediaType = InputMediaType.ANIMATION
    override fun instantiate(media: Attachment, thumb: Attachment?) = copy(media = media, thumb = thumb)
}

val InputMediaAnimation.captionEntitiesInstantiated: List<InstantiatedEntity>
    get() = captionEntities?.map { InstantiatedEntity(it, this.caption) } ?: listOf()
