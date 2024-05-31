package ski.gagar.vertigram.telegram.types

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ski.gagar.vertigram.telegram.types.colors.RgbColor
import ski.gagar.vertigram.util.NoPosArgs

/**
 * Telegram [ChatBackground](https://core.telegram.org/bots/api#chatbackground) type.
 *
 * For up-to-date documentation please consult the official Telegram docs.
 */
data class ChatBackground(
    @JsonIgnore
    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
    val type: Type
) {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)
    @JsonSubTypes(
        JsonSubTypes.Type(value = Type.Fill::class, name = Kind.FILL_STR),
        JsonSubTypes.Type(value = Type.Wallpaper::class, name = Kind.WALLPAPER_STR),
        JsonSubTypes.Type(value = Type.Pattern::class, name = Kind.PATTERN_STR),
        JsonSubTypes.Type(value = Type.ChatTheme::class, name = Kind.CHAT_THEME_STR),
    )
    sealed interface Type {
        val type: Kind

        /**
         * Telegram [BackgroundFill](https://core.telegram.org/bots/api#backgroundfill) type.
         *
         * For up-to-date documentation please consult the official Telegram docs.
         */
        data class Fill(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            val fill: Value,
            val darkThemeDimming: Int
        ) : Type {
            override val type: Kind = Kind.FILL

            @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)
            @JsonSubTypes(
                JsonSubTypes.Type(value = Value.Solid::class, name = Type.SOLID_STR),
                JsonSubTypes.Type(value = Value.Gradient::class, name = Type.GRADIENT_STR),
                JsonSubTypes.Type(value = Value.FreeformGradient::class, name = Type.FREEFORM_GRADIENT_STR),
            )
            sealed interface Value {
                val type: Type

                /**
                 * Telegram [BackgroundFillSolid](https://core.telegram.org/bots/api#backgroundfillsolid) type.
                 *
                 * For up-to-date documentation please consult the official Telegram docs.
                 */
                data class Solid(
                    @JsonIgnore
                    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
                    val color: RgbColor
                ) : Value {
                    override val type: Type = Type.SOLID
                }

                /**
                 * Telegram [BackgroundFillGradient](https://core.telegram.org/bots/api#backgroundfillgradient) type.
                 *
                 * For up-to-date documentation please consult the official Telegram docs.
                 */
                data class Gradient(
                    @JsonIgnore
                    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
                    val topColor: RgbColor,
                    val bottomColor: RgbColor,
                    val rotationAngle: Int
                ) : Value {
                    override val type: Type = Type.GRADIENT
                }

                /**
                 * Telegram [BackgroundFillFreeformGradient](https://core.telegram.org/bots/api#backgroundfillfreeformgradient) type.
                 *
                 * For up-to-date documentation please consult the official Telegram docs.
                 */
                data class FreeformGradient(
                    @JsonIgnore
                    private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
                    val colors: List<RgbColor>
                ) : Value {
                    override val type: Type = Type.FREEFORM_GRADIENT
                }
            }

            /**
             * A value for [type]
             */
            enum class Type {
                @JsonProperty(SOLID_STR)
                SOLID,
                @JsonProperty(GRADIENT_STR)
                GRADIENT,
                @JsonProperty(FREEFORM_GRADIENT_STR)
                FREEFORM_GRADIENT;

                companion object {
                    const val SOLID_STR = "solid"
                    const val GRADIENT_STR = "gradient"
                    const val FREEFORM_GRADIENT_STR = "freeform"
                }
            }
        }

        /**
         * Telegram [BackgroundTypeWallpaper](https://core.telegram.org/bots/api#backgroundtypewallpaper) type.
         *
         * For up-to-date documentation please consult the official Telegram docs.
         */
        data class Wallpaper(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            val document: Document,
            val darkThemeDimming: Int,
            @get:JvmName("getIsBlurred")
            val isBlurred: Boolean = false,
            @get:JvmName("getIsMoving")
            val isMoving: Boolean = false,
        ) : Type {
            override val type: Kind = Kind.WALLPAPER
        }

        /**
         * Telegram [BackgroundTypePattern](https://core.telegram.org/bots/api#backgroundtypepattern) type.
         *
         * For up-to-date documentation please consult the official Telegram docs.
         */
        data class Pattern(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            val document: Document,
            val fill: Type.Fill.Value,
            val intensity: Int,
            @get:JvmName("getIsInverted")
            val isInverted: Boolean = false,
            @get:JvmName("getIsMoving")
            val isMoving: Boolean = false,
        ) : Type {
            override val type: Kind = Kind.PATTERN
        }

        /**
         * Telegram [BackgroundTypeChatTheme](https://core.telegram.org/bots/api#backgroundtypechattheme) type.
         *
         * For up-to-date documentation please consult the official Telegram docs.
         */
        data class ChatTheme(
            @JsonIgnore
            private val noPosArgs: NoPosArgs = NoPosArgs.INSTANCE,
            val themeName: String
        ) : Type {
            override val type: Kind = Kind.CHAT_THEME
        }
    }



    /**
     * A value for [Type.type]
     */
    enum class Kind {
        @JsonProperty(FILL_STR)
        FILL,
        @JsonProperty(WALLPAPER_STR)
        WALLPAPER,
        @JsonProperty(PATTERN_STR)
        PATTERN,
        @JsonProperty(CHAT_THEME_STR)
        CHAT_THEME;

        companion object {
            const val FILL_STR = "fill"
            const val WALLPAPER_STR = "wallpaper"
            const val PATTERN_STR = "pattern"
            const val CHAT_THEME_STR = "chat_theme"
        }
    }
}
