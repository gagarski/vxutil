package ski.gagar.vertigram.types

import java.time.Instant

data class Message(
    val messageId: Long,
    val messageThreadId: Long? = null,
    val from: User? = null,
    val senderChat: Chat? = null,
    val date: Instant,
    val chat: Chat,
    @get:JvmName("getIsAutomaticForward")
    val isAutomaticForward: Boolean = false,
    val replyToMessage: Message? = null,
    val editDate: Instant? = null,
    val hasProtectedContent: Boolean = false,
    val mediaGroupId: String? = null,
    val authorSignature: String? = null,
    val text: String? = null,
    val entities: List<MessageEntity>? = null,
    val animation: Animation? = null,
    val audio: Audio? = null,
    val document: Document? = null,
    val photo: List<PhotoSize>? = null,
    val sticker: Sticker? = null,
    val video: Video? = null,
    val videoNote: VideoNote? = null,
    val voice: Voice? = null,
    val caption: String? = null,
    val captionEntities: List<MessageEntity>? = null,
    val contact: Contact? = null,
    val dice: Dice? = null,
    val game: Game? = null,
    val poll: Poll? = null,
    val venue: Venue? = null,
    val location: Location? = null,
    val newChatMembers: List<User>? = null,
    val leftChatMember: User? = null,
    val newChatTitle: String? = null,
    val newChatPhoto: List<PhotoSize>? = null,
    val deleteChatPhoto: Boolean = false,
    val groupChatCreated: Boolean = false,
    val supergroupChatCreated: Boolean = false,
    val channelChatCreated: Boolean = false,
    val messageAutoDeleteTimerChanged: Boolean = false,
    val migrateToChatId: Long? = null,
    val migrateFromChatId: Long? = null,
    val pinnedMessage: Message? = null,
    val invoice: Invoice? = null,
    val successfulPayment: SuccessfulPayment? = null,
    val connectedWebsite: String? = null,
    val passportData: PassportData? = null,
    val proximityAlertTriggered: ProximityAlertTriggered? = null,
    val videoChatScheduled: VideoChatScheduled? = null,
    val videoChatStarted: VideoChatStarted? = null,
    val videoChatEnded: VideoChatEnded? = null,
    val videoChatParticipantsInvited: VideoChatParticipantsInvited? = null,
    val webAppData: WebAppData? = null,
    val replyMarkup: ReplyMarkup.InlineKeyboard? = null,
    @get:JvmName("getIsTopicMessage")
    val isTopicMessage: Boolean = false,
    val forumTopicCreated: ForumTopic.Created? = null,
    val forumTopicClosed: ForumTopic.Closed? = null,
    val forumTopicReopened: ForumTopic.Reopened? = null,
    // Since Telegram Bot API 6.4
    val hasMediaSpoiler: Boolean = false,
    val forumTopicEdited: ForumTopic.Edited? = null,
    val generalForumTopicHidden: ForumTopic.GeneralHidden? = null,
    val generalForumTopicUndhidden: ForumTopic.GeneralUnhidden? = null,
    val writeAccessAllowed: WriteAccessAllowed? = null,
    // Since Telegram Bot API 6.5
    val chatShared: ChatShared? = null,
    // Since Telegram Bot API 6.8
    val story: Story? = null,
    // Since Telegram Bot API 7.0
    val externalReply: ExternalReplyInfo? = null,
    val quote: TextQuote? = null,
    val linkPreviewOptions: LinkPreviewOptions? = null,
    val usersShared: UsersShared? = null,
    val giveawayCreated: Giveaway.Created? = null,
    val giveaway: Giveaway? = null,
    val giveawayWinners: Giveaway.Winners? = null,
    val giveawayCompleted: Giveaway.Completed? = null,
    val forwardOrigin: MessageOrigin? = null
)
