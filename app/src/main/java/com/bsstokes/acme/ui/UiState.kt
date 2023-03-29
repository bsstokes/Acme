package com.bsstokes.acme.ui

typealias SimpleUiState<T> = UiState<T, Unit, Unit>

val LoadingUiState = Unit.loading()
val ErrorUiState = Unit.error()

sealed class UiState<out SUCCESS, out LOADING, out ERROR> {
    internal abstract val isSuccess: Boolean
    internal abstract val isLoading: Boolean
    internal abstract val isError: Boolean

    fun isSuccess(): Boolean = isSuccess
    fun isLoading(): Boolean = isLoading
    fun isError(): Boolean = isError

    fun successOrNull(): SUCCESS? = fold(
        ifSuccess = { it },
        ifLoading = { null },
        ifError = { null },
    )

    fun loadingOrNull(): LOADING? = fold(
        ifSuccess = { null },
        ifLoading = { it },
        ifError = { null },
    )

    fun errorOrNull(): ERROR? = fold(
        ifSuccess = { null },
        ifLoading = { null },
        ifError = { it },
    )

    data class Success<out SUCCESS>(val value: SUCCESS) : UiState<SUCCESS, Nothing, Nothing>() {
        override val isSuccess: Boolean = true
        override val isLoading: Boolean = false
        override val isError: Boolean = false
    }

    data class Loading<out LOADING>(val value: LOADING) : UiState<Nothing, LOADING, Nothing>() {
        override val isSuccess: Boolean = false
        override val isLoading: Boolean = true
        override val isError: Boolean = false
    }

    data class Error<out ERROR>(val value: ERROR) : UiState<Nothing, Nothing, ERROR>() {
        override val isSuccess: Boolean = false
        override val isLoading: Boolean = false
        override val isError: Boolean = true
    }

    inline fun <T> fold(
        ifSuccess: (SUCCESS) -> T,
        ifLoading: (LOADING) -> T,
        ifError: (ERROR) -> T,
    ): T = when (this) {
        is Success -> ifSuccess(value)
        is Loading -> ifLoading(value)
        is Error -> ifError(value)
    }

    companion object {
        fun <SUCCESS> success(success: SUCCESS): UiState<SUCCESS, Nothing, Nothing> =
            Success(success)

        fun <LOADING> loading(loading: LOADING): UiState<Nothing, LOADING, Nothing> =
            Loading(loading)

        fun <ERROR> error(error: ERROR): UiState<Nothing, Nothing, ERROR> = Error(error)
    }
}

fun <SUCCESS> SUCCESS.success(): UiState<SUCCESS, Nothing, Nothing> =
    UiState.success(this)

fun <LOADING> LOADING.loading(): UiState<Nothing, LOADING, Nothing> =
    UiState.loading(this)

fun <ERROR> ERROR.error(): UiState<Nothing, Nothing, ERROR> =
    UiState.error(this)

fun <SUCCESS, LOADING, ERROR, OUT_SUCCESS> UiState<SUCCESS, LOADING, ERROR>.map(
    transform: (SUCCESS) -> OUT_SUCCESS,
) = fold(
    ifSuccess = { transform(it).success() },
    ifLoading = { it.loading() },
    ifError = { it.error() },
)
