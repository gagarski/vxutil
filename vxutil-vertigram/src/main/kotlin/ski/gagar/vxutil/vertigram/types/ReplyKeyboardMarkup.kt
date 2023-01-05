package ski.gagar.vxutil.vertigram.types

data class ReplyKeyboardMarkup(
    val keyboard: List<List<KeyboardButton>>,
    val resizeKeyboard: Boolean = false,
    val oneTimeKeyboard: Boolean = false,
    val inputFieldPlaceholder: String? = null,
    val selective: Boolean = false,
    // Since Telegram Bot API 6.4
    @get:JvmName("getIsPersistent")
    val isPersistent: Boolean = false,
) : ReplyMarkup
