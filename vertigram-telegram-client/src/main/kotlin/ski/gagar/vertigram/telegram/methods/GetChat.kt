package ski.gagar.vertigram.telegram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.telegram.throttling.HasChatId
import ski.gagar.vertigram.telegram.types.Chat
import ski.gagar.vertigram.telegram.types.util.ChatId
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [getChat](https://core.telegram.org/bots/api#getchat) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
data class GetChat(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    override val chatId: ChatId
) : JsonTelegramCallable<Chat.FullInfo>(), HasChatId
