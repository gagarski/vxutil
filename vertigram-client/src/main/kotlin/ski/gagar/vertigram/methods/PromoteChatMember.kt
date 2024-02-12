package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.throttling.HasChatId
import ski.gagar.vertigram.types.util.ChatId
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [promoteChatMember](https://core.telegram.org/bots/api#promotechatmember) method.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
data class PromoteChatMember(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    override val chatId: ChatId,
    val userId: Long,
    @get:JvmName("getIsAnonymous")
    val isAnonymous: Boolean = false,
    val canManageChat: Boolean = false,
    val canDeleteMessages: Boolean = false,
    val canManageVideoChats: Boolean = false,
    val canRestrictMembers: Boolean = false,
    val canPromoteMembers: Boolean = false,
    val canChangeInfo: Boolean = false,
    val canInviteUsers: Boolean = false,
    val canPostMessages: Boolean = false,
    val canEditMessages: Boolean = false,
    val canPinMessages: Boolean = false,
    val canPostStories: Boolean = false,
    val canEditStories: Boolean = false,
    val canDeleteStories: Boolean = false,
    val canManageTopics: Boolean = false,
) : JsonTelegramCallable<Boolean>(), HasChatId
