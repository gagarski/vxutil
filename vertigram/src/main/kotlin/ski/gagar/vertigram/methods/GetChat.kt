package ski.gagar.vertigram.methods

import ski.gagar.vertigram.annotations.TgMethod
import ski.gagar.vertigram.throttling.HasChatId
import ski.gagar.vertigram.types.Chat
import ski.gagar.vertigram.types.ChatId

@TgMethod
data class GetChat(
    override val chatId: ChatId
) : JsonTgCallable<Chat>(), HasChatId
