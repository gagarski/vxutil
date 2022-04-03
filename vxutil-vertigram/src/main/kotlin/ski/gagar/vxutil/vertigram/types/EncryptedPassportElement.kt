package ski.gagar.vxutil.vertigram.types

/**
 * Telegram type EncryptedPassportElement.
 */
data class EncryptedPassportElement(
    val type: EncryptedPassportElementType,
    val data: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val files: List<PassportFile>? = null,
    val frontSide: PassportFile? = null,
    val reverseSide: PassportFile? = null,
    val selfie: PassportFile? = null,
    val translation: List<PassportFile>? = null,
    val hash: String
)
