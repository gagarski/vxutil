package ski.gagar.vxutil.vertigram.types

data class ChatBoostSourceGiftCode(val user: User) : ChatBoostSource {
    override val source: ChatBoostSourceType = ChatBoostSourceType.GIFT_CODE
}