package ski.gagar.vertigram.eventbus.exceptions

/**
 * An exception, which is serializable vor [ski.gagar.vertigram.Vertigram.EventBus]
 *
 * Unknown exceptions (not inherited from [VertigramException]) will be mapped to this one.
 */
open class VertigramInternalException : VertigramException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}