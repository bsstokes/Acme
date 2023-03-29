package com.bsstokes.acme.ui

typealias SimpleUiState<T> = UiState<T, Unit, Unit>

val LoadingUiState = Unit.loading()
val ErrorUiState = Unit.error()

sealed class UiState<out CONTENT, out LOADING, out ERROR> {
    internal abstract val isContent: Boolean
    internal abstract val isLoading: Boolean
    internal abstract val isError: Boolean

    fun isContent(): Boolean = isContent
    fun isLoading(): Boolean = isLoading
    fun isError(): Boolean = isError

    fun contentOrNull(): CONTENT? = fold(
        isContent = { it },
        ifLoading = { null },
        ifError = { null },
    )

    fun loadingOrNull(): LOADING? = fold(
        isContent = { null },
        ifLoading = { it },
        ifError = { null },
    )

    fun errorOrNull(): ERROR? = fold(
        isContent = { null },
        ifLoading = { null },
        ifError = { it },
    )

    data class Content<out CONTENT>(val value: CONTENT) : UiState<CONTENT, Nothing, Nothing>() {
        override val isContent: Boolean = true
        override val isLoading: Boolean = false
        override val isError: Boolean = false
    }

    data class Loading<out LOADING>(val value: LOADING) : UiState<Nothing, LOADING, Nothing>() {
        override val isContent: Boolean = false
        override val isLoading: Boolean = true
        override val isError: Boolean = false
    }

    data class Error<out ERROR>(val value: ERROR) : UiState<Nothing, Nothing, ERROR>() {
        override val isContent: Boolean = false
        override val isLoading: Boolean = false
        override val isError: Boolean = true
    }

    inline fun <T> fold(
        isContent: (CONTENT) -> T,
        ifLoading: (LOADING) -> T,
        ifError: (ERROR) -> T,
    ): T = when (this) {
        is Content -> isContent(value)
        is Loading -> ifLoading(value)
        is Error -> ifError(value)
    }

    companion object {
        fun <CONTENT> content(content: CONTENT): UiState<CONTENT, Nothing, Nothing> =
            Content(content)

        fun <LOADING> loading(loading: LOADING): UiState<Nothing, LOADING, Nothing> =
            Loading(loading)

        fun <ERROR> error(error: ERROR): UiState<Nothing, Nothing, ERROR> = Error(error)
    }
}

fun <CONTENT> CONTENT.content(): UiState<CONTENT, Nothing, Nothing> =
    UiState.content(this)

fun <LOADING> LOADING.loading(): UiState<Nothing, LOADING, Nothing> =
    UiState.loading(this)

fun <ERROR> ERROR.error(): UiState<Nothing, Nothing, ERROR> =
    UiState.error(this)

fun <CONTENT, LOADING, ERROR, OUT_CONTENT> UiState<CONTENT, LOADING, ERROR>.map(
    transform: (CONTENT) -> OUT_CONTENT,
) = fold(
    isContent = { transform(it).content() },
    ifLoading = { it.loading() },
    ifError = { it.error() },
)
