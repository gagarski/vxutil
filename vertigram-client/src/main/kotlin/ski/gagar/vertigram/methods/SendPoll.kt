package ski.gagar.vertigram.methods

import com.fasterxml.jackson.annotation.JsonIgnore
import ski.gagar.vertigram.annotations.TelegramCodegen
import ski.gagar.vertigram.annotations.TelegramMethod
import ski.gagar.vertigram.throttling.HasChatId
import ski.gagar.vertigram.throttling.Throttled
import ski.gagar.vertigram.types.Message
import ski.gagar.vertigram.types.MessageEntity
import ski.gagar.vertigram.types.Poll
import ski.gagar.vertigram.types.ReplyMarkup
import ski.gagar.vertigram.types.ReplyParameters
import ski.gagar.vertigram.types.richtext.HasOptionalRichExplanation
import ski.gagar.vertigram.types.richtext.RichText
import ski.gagar.vertigram.types.util.ChatId
import ski.gagar.vertigram.util.NoPosArgs
import java.time.Duration
import java.time.Instant

/**
 * Telegram [sendPoll](https://core.telegram.org/bots/api#sendpoll) method.
 *
 * Telegram method is divided into two virtual methods: sendPoll and sendQuiz,
 * each of them has a separate cases regarding close date options.
 * The method name is changed for quiz polls because the parameter names do
 * not represent the quiz nature of the poll naturally.
 *
 * Each case has multiple mutually-exclusive cases for different auto-closure settings.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
sealed class SendPoll : JsonTelegramCallable<Message>(), HasChatId {
    abstract val messageThreadId: Long?
    abstract val question: String
    abstract val options: List<String>
    abstract val isAnonymous: Boolean
    abstract val allowsMultipleAnswers: Boolean
    abstract val disableNotification: Boolean
    abstract val protectContent: Boolean
    abstract val replyParameters: ReplyParameters?
    abstract val replyMarkup: ReplyMarkup?
    abstract val type: Poll.Type
    @get:JvmName("getIsClosed")
    abstract val isClosed: Boolean
    /**
     * Cases for regular poll
     */
    @TelegramMethod(
        methodName = "sendPoll"
    )
    @TelegramCodegen(
        methodName = "sendPoll",
        generatePseudoConstructor = true,
        pseudoConstructorName = "SendPoll"
    )
    @Throttled
    sealed class Regular : SendPoll() {
        /**
         * Case with [openPeriod] field
         */
        data class OpenPeriod internal constructor(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            override val chatId: ChatId,
            override val messageThreadId: Long? = null,
            override val question: String,
            override val options: List<String>,
            @get:JvmName("getIsAnonymous")
            override val isAnonymous: Boolean = Defaults.isAnonymous,
            override val allowsMultipleAnswers: Boolean = false,
            val openPeriod: Duration,
            override val disableNotification: Boolean = false,
            override val protectContent: Boolean = false,
            override val replyParameters: ReplyParameters? = null,
            override val replyMarkup: ReplyMarkup? = null,

        ) : Regular() {
            override val type = Poll.Type.REGULAR
            @get:JvmName("getIsClosed")
            override val isClosed: Boolean = false

            object Defaults {
                const val isAnonymous: Boolean = true
            }
        }

        /**
         * Case with [closeDate] field
         */
        @TelegramMethod(
            methodName = "sendPoll"
        )
        @TelegramCodegen(
            methodName = "sendPoll",
            generatePseudoConstructor = true,
            pseudoConstructorName = "SendPoll"
        )
        @Throttled
        data class CloseDate internal constructor(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            override val chatId: ChatId,
            override val messageThreadId: Long? = null,
            override val question: String,
            override val options: List<String>,
            @get:JvmName("getIsAnonymous")
            override val isAnonymous: Boolean = Defaults.isAnonymous,
            override val allowsMultipleAnswers: Boolean = false,
            val closeDate: Instant,
            override val disableNotification: Boolean = false,
            override val protectContent: Boolean = false,
            override val replyParameters: ReplyParameters? = null,
            override val replyMarkup: ReplyMarkup? = null
        ) : Regular() {
            override val type = Poll.Type.REGULAR
            @get:JvmName("getIsClosed")
            override val isClosed = false

            object Defaults {
                const val isAnonymous: Boolean = true
            }
        }

        /**
         * Case with no openPeriod and closeDate field
         */
        @TelegramMethod(
            methodName = "sendPoll"
        )
        @TelegramCodegen(
            methodName = "sendPoll",
            generatePseudoConstructor = true,
            pseudoConstructorName = "SendPoll"
        )
        @Throttled
        data class Indefinite internal constructor(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            override val chatId: ChatId,
            override val messageThreadId: Long? = null,
            override val question: String,
            override val options: List<String>,
            @get:JvmName("getIsAnonymous")
            override val isAnonymous: Boolean = Defaults.isAnonymous,
            override val allowsMultipleAnswers: Boolean = false,
            override val isClosed: Boolean = false,
            override val disableNotification: Boolean = false,
            override val protectContent: Boolean = false,
            override val replyParameters: ReplyParameters? = null,
            override val replyMarkup: ReplyMarkup? = null
        ) : Regular() {
            override val type = Poll.Type.REGULAR

            object Defaults {
                const val isAnonymous: Boolean = true
            }
        }
    }

    /**
     * Cases for quiz
     */
    sealed class Quiz : SendPoll(), HasOptionalRichExplanation {
        /**
         * Case with [openPeriod] field
         */
        @TelegramMethod(
            methodName = "sendPoll"
        )
        @TelegramCodegen(
            methodName = "sendQuiz",
            docMethodName = "sendPoll",
            generatePseudoConstructor = true,
            pseudoConstructorName = "SendQuiz"
        )
        @Throttled
        data class OpenPeriod internal constructor(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            override val chatId: ChatId,
            override val messageThreadId: Long? = null,
            override val question: String,
            override val options: List<String>,
            @get:JvmName("getIsAnonymous")
            override val isAnonymous: Boolean = Defaults.isAnonymous,
            val correctOptionId: Int,
            override val explanation: String? = null,
            override val explanationParseMode: RichText.ParseMode? = null,
            override val explanationEntities: List<MessageEntity>? = null,
            val openPeriod: Duration,
            override val disableNotification: Boolean = false,
            override val protectContent: Boolean = false,
            override val replyParameters: ReplyParameters? = null,
            override val replyMarkup: ReplyMarkup? = null
        ) : Quiz() {
            override val type = Poll.Type.QUIZ
            @get:JvmName("getIsClosed")
            override val isClosed = false
            override val allowsMultipleAnswers: Boolean = false

            object Defaults {
                const val isAnonymous: Boolean = true
            }
        }

        /**
         * Case with [closeDate] field
         */
        @TelegramMethod(
            methodName = "sendPoll"
        )
        @TelegramCodegen(
            methodName = "sendQuiz",
            docMethodName = "sendPoll",
            generatePseudoConstructor = true,
            pseudoConstructorName = "SendQuiz"
        )
        @Throttled
        data class CloseDate internal constructor(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            override val chatId: ChatId,
            override val messageThreadId: Long? = null,
            override val question: String,
            override val options: List<String>,
            @get:JvmName("getIsAnonymous")
            override val isAnonymous: Boolean = Defaults.isAnonymous,
            val correctOptionId: Int,
            override val explanation: String? = null,
            override val explanationParseMode: RichText.ParseMode? = null,
            override val explanationEntities: List<MessageEntity>? = null,
            val closeDate: Instant,
            override val disableNotification: Boolean = false,
            override val protectContent: Boolean = false,
            override val replyParameters: ReplyParameters? = null,
            override val replyMarkup: ReplyMarkup? = null
        ) : Quiz() {
            override val type = Poll.Type.QUIZ
            @get:JvmName("getIsClosed")
            override val isClosed = false
            override val allowsMultipleAnswers: Boolean = false

            object Defaults {
                const val isAnonymous: Boolean = true
            }
        }

        /**
         * Case with no openPeriod and closeDate field
         */
        @TelegramMethod(
            methodName = "sendPoll"
        )
        @TelegramCodegen(
            methodName = "sendQuiz",
            docMethodName = "sendPoll",
            generatePseudoConstructor = true,
            pseudoConstructorName = "SendQuiz"
        )
        @Throttled
        data class Indefinite internal constructor(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            override val chatId: ChatId,
            override val messageThreadId: Long? = null,
            override val question: String,
            override val options: List<String>,
            @get:JvmName("getIsAnonymous")
            override val isAnonymous: Boolean = Defaults.isAnonymous,
            val correctOptionId: Int,
            override val explanation: String? = null,
            override val explanationParseMode: RichText.ParseMode? = null,
            override val explanationEntities: List<MessageEntity>? = null,
            @get:JvmName("getIsClosed")
            override val isClosed: Boolean = false,
            override val disableNotification: Boolean = false,
            override val protectContent: Boolean = false,
            override val replyParameters: ReplyParameters? = null,
            override val replyMarkup: ReplyMarkup? = null
        ) : Quiz() {
            override val type = Poll.Type.QUIZ
            override val allowsMultipleAnswers: Boolean = false

            object Defaults {
                const val isAnonymous: Boolean = true
            }
        }
    }

}
