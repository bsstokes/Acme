package com.bsstokes.acme.domain.response

val SuccessResponse = Unit.success()
val ErrorResponse = Unit.error()

typealias SimpleResponse<T> = Response<T, Unit>

sealed class Response<out VALUE, out ERROR> {
    data class Success<VALUE>(val value: VALUE) : Response<VALUE, Nothing>()
    data class Error<ERROR>(val error: ERROR) : Response<Nothing, ERROR>()

    inline fun <T> fold(ifSuccess: (VALUE) -> T, ifError: (ERROR) -> T): T = when (this) {
        is Success -> ifSuccess(value)
        is Error -> ifError(error)
    }

    fun getOrNull(): VALUE? = fold(
        ifSuccess = { it },
        ifError = { null },
    )

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
}

fun <VALUE> VALUE.success(): Response<VALUE, Nothing> = Response.Success(this)
fun <ERROR> ERROR.error(): Response<Nothing, ERROR> = Response.Error(this)

fun <VALUE, ERROR, OUT_VALUE> Response<VALUE, ERROR>.map(
    transform: (VALUE) -> OUT_VALUE,
): Response<OUT_VALUE, ERROR> = fold(
    ifSuccess = { transform(it).success() },
    ifError = { it.error() },
)

inline fun <VALUE, ERROR> Response<VALUE, ERROR>.onSuccess(
    action: (value: VALUE) -> Unit,
): Response<VALUE, ERROR> = fold(
    ifSuccess = { action(it); this },
    ifError = { this },
)

inline fun <VALUE, ERROR> Response<VALUE, ERROR>.onError(
    action: (error: ERROR) -> Unit,
): Response<VALUE, ERROR> = fold(
    ifSuccess = { this },
    ifError = { action(it); this },
)

/** Returns [error] if the receiver is null, and returns the result of [success] otherwise. */
fun <VALUE, ERROR> VALUE?.successOrElse(error: Response<Nothing, ERROR>): Response<VALUE, ERROR> =
    this?.success() ?: error

/**
 * Returns [error] if the receiver is null, and returns the result of [success] otherwise. This
 * version calls [com.bsstokes.acme.domain.response.error] to convert [error] into a [Response].
 */
fun <VALUE, ERROR> VALUE?.successOrElse(error: ERROR): Response<VALUE, ERROR> =
    this?.success() ?: error.error()

/** Convenience function for calling [successOrElse] with [ErrorResponse]. */
fun <VALUE> VALUE?.successOrErrorResponse(): SimpleResponse<VALUE> =
    successOrElse(ErrorResponse)
