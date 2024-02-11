package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.types.ChatAdministratorRights
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [setMyDefaultAdministratorRights](https://core.telegram.org/bots/api#setmydefaultadministratorrights) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
data class SetMyDefaultAdministratorRights(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    val rights: ChatAdministratorRights? = null,
    val forChannels: Boolean = false
) : JsonTelegramCallable<Boolean>()
