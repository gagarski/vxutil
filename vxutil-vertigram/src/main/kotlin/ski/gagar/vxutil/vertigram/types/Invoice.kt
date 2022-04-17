package ski.gagar.vxutil.vertigram.types

data class Invoice(
    val title: String,
    val description: String,
    val startParameter: String,
    val currency: String,
    val totalAmount: Int
)
