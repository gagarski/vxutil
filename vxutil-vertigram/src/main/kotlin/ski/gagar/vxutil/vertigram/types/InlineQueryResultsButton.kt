package ski.gagar.vxutil.vertigram.types

data class InlineQueryResultsButton(
    val text: String,
    val webApp: WebAppInfo? = null,
    val startParameter: String? = null
)
