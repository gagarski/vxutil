package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.throttling.HasChatId
import ski.gagar.vertigram.types.ChatId
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [deleteMessages](https://core.telegram.org/bots/api#deletemessages) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
data class DeleteMessages(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    override val chatId: ChatId,
    val messageIds: List<Long>
) : JsonTelegramCallable<Boolean>(), HasChatId
