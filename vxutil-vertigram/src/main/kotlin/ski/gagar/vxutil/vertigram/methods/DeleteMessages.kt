package ski.gagar.vxutil.vertigram.methods

import ski.gagar.vertigram.annotations.TgMethod
import ski.gagar.vxutil.vertigram.throttling.HasChatId
import ski.gagar.vxutil.vertigram.types.ChatId

@TgMethod
data class DeleteMessages(
    override val chatId: ChatId,
    val messageIds: List<Long>
) : JsonTgCallable<Boolean>(), HasChatId
