package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.annotations.TgMethod
import ski.gagar.vertigram.types.Message
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [deleteStickerSet](https://core.telegram.org/bots/api#deletestickerset) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
@TgMethod
data class DeleteStickerSet(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    val name: String
) : MultipartTgCallable<Message>()
