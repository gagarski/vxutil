package ski.gagar.vxutil.vertigram.types

data class Document(
    val fileId: String,
    val fileUniqueId: String,
    val thumb: PhotoSize? = null,
    val fileName: String? = null,
    val mimeType: String? = null,
    val fileSize: Long? = null
)
