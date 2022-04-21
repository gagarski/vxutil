package ski.gagar.vxutil.vertigram.types

import java.time.Instant

data class ChatMemberUpdated(
    val chat: Chat,
    val from: User,
    val date: Instant,
    val oldChatMember: ChatMember,
    val newChatMember: ChatMember,
    val inviteLink: ChatInviteLink? = null
)
