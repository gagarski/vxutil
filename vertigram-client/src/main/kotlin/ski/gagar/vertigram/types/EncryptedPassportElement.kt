package ski.gagar.vertigram.types

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [EncryptedPassportElement](https://core.telegram.org/bots/api#encryptedpassportelement) type.
 *
 * Subtypes are introduced to represent document types for [type], given each of them has its own set
 * of mandatory an optional fields described [here](https://core.telegram.org/passport#fields).
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
@JsonIgnoreProperties(value = ["type"])
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = EncryptedPassportElement.PersonalDetails::class, name = EncryptedPassportElement.Type.PERSONAL_DETAILS_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.Passport::class, name = EncryptedPassportElement.Type.PASSPORT_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.DriverLicense::class, name = EncryptedPassportElement.Type.DRIVER_LICENSE_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.IdentityCard::class, name = EncryptedPassportElement.Type.IDENTITY_CARD_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.InternalPassport::class, name = EncryptedPassportElement.Type.INTERNAL_PASSPORT_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.Address::class, name = EncryptedPassportElement.Type.ADDRESS_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.UtilityBill::class, name = EncryptedPassportElement.Type.UTILITY_BILL_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.BankStatement::class, name = EncryptedPassportElement.Type.BANK_STATEMENT_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.RentalAgreement::class, name = EncryptedPassportElement.Type.RENTAL_AGREEMENT_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.PassportRegistration::class, name = EncryptedPassportElement.Type.PASSPORT_REGISTRATION_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.TemporaryRegistration::class, name = EncryptedPassportElement.Type.TEMPORARY_REGISTRATION_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.PhoneNumber::class, name = EncryptedPassportElement.Type.PHONE_NUMBER_STR),
    JsonSubTypes.Type(value = EncryptedPassportElement.Email::class, name = EncryptedPassportElement.Type.EMAIL_STR),
)
interface EncryptedPassportElement {
    val type: Type

    /**
     * Personal Details case
     */
    data class PersonalDetails(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val data: String,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.PERSONAL_DETAILS
    }

    /**
     * Passport case
     */
    data class Passport(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val data: String,
        val frontSide: PassportFile,
        val selfie: PassportFile? = null,
        val translation: List<PassportFile>? = null,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.PASSPORT
    }

    /**
     * Driver License case
     */
    data class DriverLicense(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val data: String,
        val frontSide: PassportFile,
        val reverseSide: PassportFile,
        val selfie: PassportFile? = null,
        val translation: List<PassportFile>? = null,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.DRIVER_LICENSE
    }

    /**
     * Identity Card case
     */
    data class IdentityCard(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val data: String,
        val frontSide: PassportFile,
        val reverseSide: PassportFile,
        val selfie: PassportFile? = null,
        val translation: List<PassportFile>? = null,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.IDENTITY_CARD
    }

    /**
     * Internal Passport case
     */
    data class InternalPassport(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val data: String,
        val frontSide: PassportFile,
        val selfie: PassportFile? = null,
        val translation: List<PassportFile>? = null,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.INTERNAL_PASSPORT
    }

    /**
     * Address case
     */
    data class Address(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val data: String,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.ADDRESS
    }

    /**
     * Utility Bill case
     */
    data class UtilityBill(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val files: List<PassportFile>,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.UTILITY_BILL
    }

    /**
     * Bank Statement case
     */
    data class BankStatement(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val files: List<PassportFile>,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.BANK_STATEMENT
    }

    /**
     * Rental Agreement case
     */
    data class RentalAgreement(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val files: List<PassportFile>,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.RENTAL_AGREEMENT
    }

    /**
     * Passport Registration case
     */
    data class PassportRegistration(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val files: List<PassportFile>,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.PASSPORT_REGISTRATION
    }

    /**
     * Temporary Registration case
     */
    data class TemporaryRegistration(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val files: List<PassportFile>,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.TEMPORARY_REGISTRATION
    }

    /**
     * Phone Number case
     */
    data class PhoneNumber(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val phoneNumber: String,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.PHONE_NUMBER
    }

    /**
     * Phone Number case
     */
    data class Email(
        @JsonIgnore
        private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
        val email: String,
        val hash: String
    ) : EncryptedPassportElement {
        override val type: Type = Type.EMAIL
    }

    /**
     * A value for [EncryptedPassportElement.type]
     */
    enum class Type {
        @JsonProperty(PERSONAL_DETAILS_STR)
        PERSONAL_DETAILS,
        @JsonProperty(PASSPORT_STR)
        PASSPORT,
        @JsonProperty(DRIVER_LICENSE_STR)
        DRIVER_LICENSE,
        @JsonProperty(IDENTITY_CARD_STR)
        IDENTITY_CARD,
        @JsonProperty(INTERNAL_PASSPORT_STR)
        INTERNAL_PASSPORT,
        @JsonProperty(ADDRESS_STR)
        ADDRESS,
        @JsonProperty(UTILITY_BILL_STR)
        UTILITY_BILL,
        @JsonProperty(BANK_STATEMENT_STR)
        BANK_STATEMENT,
        @JsonProperty(RENTAL_AGREEMENT_STR)
        RENTAL_AGREEMENT,
        @JsonProperty(PASSPORT_REGISTRATION_STR)
        PASSPORT_REGISTRATION,
        @JsonProperty(TEMPORARY_REGISTRATION_STR)
        TEMPORARY_REGISTRATION,
        @JsonProperty(PHONE_NUMBER_STR)
        PHONE_NUMBER,
        @JsonProperty(EMAIL_STR)
        EMAIL;

        companion object {
            const val PERSONAL_DETAILS_STR = "personal_details"
            const val PASSPORT_STR = "passport"
            const val DRIVER_LICENSE_STR = "driver_license"
            const val IDENTITY_CARD_STR = "identity_card"
            const val INTERNAL_PASSPORT_STR = "internal_passport"
            const val ADDRESS_STR = "address"
            const val UTILITY_BILL_STR = "utility_bill"
            const val BANK_STATEMENT_STR = "bank_statement"
            const val RENTAL_AGREEMENT_STR = "rental_agreement"
            const val PASSPORT_REGISTRATION_STR = "passport_registration"
            const val TEMPORARY_REGISTRATION_STR = "temporary_registration"
            const val PHONE_NUMBER_STR = "phone_number"
            const val EMAIL_STR = "email"
        }
    }

}
