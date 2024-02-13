package ski.gagar.vertigram.types

data class PassportElementErrorFrontSide(
    val type: EncryptedPassportElement.Type,
    val fileHash: String,
    val message: String
) : PassportElementError {
    override val source: PassportElementErrorSource = PassportElementErrorSource.FRONT_SIDE
}
