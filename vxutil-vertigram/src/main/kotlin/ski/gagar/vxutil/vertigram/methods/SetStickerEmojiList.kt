package ski.gagar.vxutil.vertigram.methods

import ski.gagar.vertigram.annotations.TgMethod
import ski.gagar.vxutil.vertigram.types.Message

@TgMethod
data class SetStickerEmojiList(
    val sticker: String,
    val emojiList: List<String>
) : MultipartTgCallable<Message>()
