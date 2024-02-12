package ski.gagar.vertigram.types

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [ChosenInlineResult](https://core.telegram.org/bots/api#choseninlineresult) type.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
data class ChosenInlineResult(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    val resultId: String,
    val from: User,
    val location: Location? = null,
    val inlineMessageId: String? = null,
    val query: String
)
