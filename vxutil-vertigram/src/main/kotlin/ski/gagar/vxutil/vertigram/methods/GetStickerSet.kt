package ski.gagar.vxutil.vertigram.methods

import ski.gagar.vertigram.annotations.TgMethod
import ski.gagar.vxutil.vertigram.types.StickerSet

@TgMethod
data class GetStickerSet(
    val name: String
) : JsonTgCallable<StickerSet>
