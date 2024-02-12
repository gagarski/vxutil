package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.throttling.HasChatId
import ski.gagar.vertigram.types.util.ChatId
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [exportChatInviteLink](https://core.telegram.org/bots/api#exportchatinvitelink) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
data class ExportChatInviteLink(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    override val chatId: ChatId
) : JsonTelegramCallable<String>(), HasChatId
