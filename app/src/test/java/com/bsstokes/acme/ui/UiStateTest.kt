package com.bsstokes.acme.ui

import com.bsstokes.acme.test.assertFalse
import com.bsstokes.acme.test.assertNull
import com.bsstokes.acme.test.assertTrue
import com.bsstokes.acme.test.shouldEqual
import com.bsstokes.acme.test.shouldNotEqual
import org.junit.Test

class UiStateTest {

    @Test
    fun `Content is content`() {
        UiState.Content("yes").isContent().assertTrue()
        UiState.Content("no").isLoading().assertFalse()
        UiState.Content("no").isError().assertFalse()
    }

    @Test
    fun `Loading is loading`() {
        UiState.Loading("no").isContent().assertFalse()
        UiState.Loading("yes").isLoading().assertTrue()
        UiState.Loading("no").isError().assertFalse()
    }

    @Test
    fun `Error is error`() {
        UiState.Error("no").isContent().assertFalse()
        UiState.Error("no").isLoading().assertFalse()
        UiState.Error("yes").isError().assertTrue()
    }

    @Test
    fun `companion content creates a Content`() {
        UiState.content("content") shouldEqual UiState.Content("content")
        UiState.content("content") shouldNotEqual UiState.Loading("content")
        UiState.content("content") shouldNotEqual UiState.Error("content")
    }

    @Test
    fun `companion loading creates a Loading`() {
        UiState.loading("loading") shouldNotEqual UiState.Content("loading")
        UiState.loading("loading") shouldEqual UiState.Loading("loading")
        UiState.loading("loading") shouldNotEqual UiState.Error("loading")
    }

    @Test
    fun `companion error creates a Error`() {
        UiState.error("error") shouldNotEqual UiState.Content("error")
        UiState.error("error") shouldNotEqual UiState.Loading("error")
        UiState.error("error") shouldEqual UiState.Error("error")
    }

    @Test
    fun `content extension creates a Content`() {
        "content".content() shouldEqual UiState.Content("content")
        "content".content() shouldNotEqual UiState.Loading("content")
        "content".content() shouldNotEqual UiState.Error("content")
    }

    @Test
    fun `loading extension creates a Loading`() {
        "loading".loading() shouldNotEqual UiState.Content("loading")
        "loading".loading() shouldEqual UiState.Loading("loading")
        "loading".loading() shouldNotEqual UiState.Error("loading")
    }

    @Test
    fun `error extension creates a Error`() {
        "error".error() shouldNotEqual UiState.Content("error")
        "error".error() shouldNotEqual UiState.Loading("error")
        "error".error() shouldEqual UiState.Error("error")
    }

    @Test
    fun `content or null`() {
        UiState.Content("value").contentOrNull() shouldEqual "value"
        UiState.Loading("value").contentOrNull().assertNull()
        UiState.Error("value").contentOrNull().assertNull()
    }

    @Test
    fun `loading or null`() {
        UiState.Content("value").loadingOrNull().assertNull()
        UiState.Loading("value").loadingOrNull() shouldEqual "value"
        UiState.Error("value").loadingOrNull().assertNull()
    }

    @Test
    fun `error or null`() {
        UiState.Content("value").errorOrNull().assertNull()
        UiState.Loading("value").errorOrNull().assertNull()
        UiState.Error("value").errorOrNull() shouldEqual "value"
    }

    @Test
    fun `fold with Content value`() {
        var ifContentCalled: Int? = null
        var ifLoadingCalled: String? = null
        var ifErrorCalled: Throwable? = null

        val first: UiState<Int, String, Throwable> = 1.content()
        val result = first.fold(
            isContent = { ifContentCalled = it; it },
            ifLoading = { ifLoadingCalled = it; it },
            ifError = { ifErrorCalled = it; it },
        )

        ifContentCalled shouldEqual 1
        ifLoadingCalled.assertNull()
        ifErrorCalled.assertNull()
        result shouldEqual 1
    }

    @Test
    fun `fold with Loading value`() {
        var ifContentCalled: Int? = null
        var ifLoadingCalled: String? = null
        var ifErrorCalled: Throwable? = null

        val second: UiState<Int, String, Throwable> = "one".loading()
        val result = second.fold(
            isContent = { ifContentCalled = it; it },
            ifLoading = { ifLoadingCalled = it; it },
            ifError = { ifErrorCalled = it; it },
        )

        ifContentCalled.assertNull()
        ifLoadingCalled shouldEqual "one"
        ifErrorCalled.assertNull()
        result shouldEqual "one"
    }

    @Test
    fun `fold with Error value`() {
        var ifContentCalled: Int? = null
        var ifLoadingCalled: String? = null
        var ifErrorCalled: Throwable? = null

        val throwable = Throwable("oops")
        val second: UiState<Int, String, Throwable> = throwable.error()
        val result = second.fold(
            isContent = { ifContentCalled = it; it },
            ifLoading = { ifLoadingCalled = it; it },
            ifError = { ifErrorCalled = it; it },
        )

        ifContentCalled.assertNull()
        ifLoadingCalled.assertNull()
        ifErrorCalled shouldEqual throwable
        result shouldEqual throwable
    }

    @Test
    fun `map returns transformed Content on Content`() {
        val viewState: UiState<String, Int, Throwable> = "Texas wins!".content()
        viewState.map { it.uppercase() } shouldEqual "TEXAS WINS!".content()
    }

    @Test
    fun `map returns Loading on Loading`() {
        val viewState: UiState<String, Int, Throwable> = 2.loading()
        viewState.map { it.uppercase() } shouldEqual 2.loading()
    }

    @Test
    fun `map returns Error on Error`() {
        val throwable = Throwable()
        val viewState: UiState<String, Int, Throwable> = throwable.error()
        viewState.map { it.uppercase() } shouldEqual throwable.error()
    }

    private object Error
    private object Loading
    private data class Product(val name: String)

    private fun productFound(product: Product) = product.content()
    private fun productLoading(): UiState<Product, Loading, Error> = Loading.loading()
    private fun productNotFound(): UiState<Product, Loading, Error> = Error.error()

    @Test
    fun `example with Product as content`() {
        val result = productFound(Product("Shirt")).fold(
            isContent = { it.name },
            ifLoading = { "Loading" },
            ifError = { "Not found" },
        )

        result shouldEqual "Shirt"
    }

    @Test
    fun `example with Product as loading`() {
        val result = productLoading().fold(
            isContent = { it.name },
            ifLoading = { "Loading" },
            ifError = { "Not found" },
        )

        result shouldEqual "Loading"
    }

    @Test
    fun `example with Product as error`() {
        val result = productNotFound().fold(
            isContent = { it.name },
            ifLoading = { "Loading" },
            ifError = { "Not found" },
        )

        result shouldEqual "Not found"
    }
}
