package com.bsstokes.acme.ui

import com.bsstokes.acme.test.assertFalse
import com.bsstokes.acme.test.assertNull
import com.bsstokes.acme.test.assertTrue
import com.bsstokes.acme.test.shouldEqual
import com.bsstokes.acme.test.shouldNotEqual
import org.junit.Test

class UiStateTest {

    @Test
    fun `Success is success`() {
        UiState.Success("yes").isSuccess().assertTrue()
        UiState.Success("no").isLoading().assertFalse()
        UiState.Success("no").isError().assertFalse()
    }

    @Test
    fun `Loading is loading`() {
        UiState.Loading("no").isSuccess().assertFalse()
        UiState.Loading("yes").isLoading().assertTrue()
        UiState.Loading("no").isError().assertFalse()
    }

    @Test
    fun `Error is error`() {
        UiState.Error("no").isSuccess().assertFalse()
        UiState.Error("no").isLoading().assertFalse()
        UiState.Error("yes").isError().assertTrue()
    }

    @Test
    fun `companion success creates a Success`() {
        UiState.success("success") shouldEqual UiState.Success("success")
        UiState.success("success") shouldNotEqual UiState.Loading("success")
        UiState.success("success") shouldNotEqual UiState.Error("success")
    }

    @Test
    fun `companion loading creates a Loading`() {
        UiState.loading("loading") shouldNotEqual UiState.Success("loading")
        UiState.loading("loading") shouldEqual UiState.Loading("loading")
        UiState.loading("loading") shouldNotEqual UiState.Error("loading")
    }

    @Test
    fun `companion error creates a Error`() {
        UiState.error("error") shouldNotEqual UiState.Success("error")
        UiState.error("error") shouldNotEqual UiState.Loading("error")
        UiState.error("error") shouldEqual UiState.Error("error")
    }

    @Test
    fun `success extension creates a Success`() {
        "success".success() shouldEqual UiState.Success("success")
        "success".success() shouldNotEqual UiState.Loading("success")
        "success".success() shouldNotEqual UiState.Error("success")
    }

    @Test
    fun `loading extension creates a Loading`() {
        "loading".loading() shouldNotEqual UiState.Success("loading")
        "loading".loading() shouldEqual UiState.Loading("loading")
        "loading".loading() shouldNotEqual UiState.Error("loading")
    }

    @Test
    fun `error extension creates a Error`() {
        "error".error() shouldNotEqual UiState.Success("error")
        "error".error() shouldNotEqual UiState.Loading("error")
        "error".error() shouldEqual UiState.Error("error")
    }

    @Test
    fun `success or null`() {
        UiState.Success("value").successOrNull() shouldEqual "value"
        UiState.Loading("value").successOrNull().assertNull()
        UiState.Error("value").successOrNull().assertNull()
    }

    @Test
    fun `loading or null`() {
        UiState.Success("value").loadingOrNull().assertNull()
        UiState.Loading("value").loadingOrNull() shouldEqual "value"
        UiState.Error("value").loadingOrNull().assertNull()
    }

    @Test
    fun `error or null`() {
        UiState.Success("value").errorOrNull().assertNull()
        UiState.Loading("value").errorOrNull().assertNull()
        UiState.Error("value").errorOrNull() shouldEqual "value"
    }

    @Test
    fun `fold with Success value`() {
        var ifSuccessCalled: Int? = null
        var ifLoadingCalled: String? = null
        var ifErrorCalled: Throwable? = null

        val first: UiState<Int, String, Throwable> = 1.success()
        val result = first.fold(
            ifSuccess = { ifSuccessCalled = it; it },
            ifLoading = { ifLoadingCalled = it; it },
            ifError = { ifErrorCalled = it; it },
        )

        ifSuccessCalled shouldEqual 1
        ifLoadingCalled.assertNull()
        ifErrorCalled.assertNull()
        result shouldEqual 1
    }

    @Test
    fun `fold with Loading value`() {
        var ifSuccessCalled: Int? = null
        var ifLoadingCalled: String? = null
        var ifErrorCalled: Throwable? = null

        val second: UiState<Int, String, Throwable> = "one".loading()
        val result = second.fold(
            ifSuccess = { ifSuccessCalled = it; it },
            ifLoading = { ifLoadingCalled = it; it },
            ifError = { ifErrorCalled = it; it },
        )

        ifSuccessCalled.assertNull()
        ifLoadingCalled shouldEqual "one"
        ifErrorCalled.assertNull()
        result shouldEqual "one"
    }

    @Test
    fun `fold with Error value`() {
        var ifSuccessCalled: Int? = null
        var ifLoadingCalled: String? = null
        var ifErrorCalled: Throwable? = null

        val throwable = Throwable("oops")
        val second: UiState<Int, String, Throwable> = throwable.error()
        val result = second.fold(
            ifSuccess = { ifSuccessCalled = it; it },
            ifLoading = { ifLoadingCalled = it; it },
            ifError = { ifErrorCalled = it; it },
        )

        ifSuccessCalled.assertNull()
        ifLoadingCalled.assertNull()
        ifErrorCalled shouldEqual throwable
        result shouldEqual throwable
    }

    @Test
    fun `map returns transformed Success on Success`() {
        val viewState: UiState<String, Int, Throwable> = "Texas wins!".success()
        viewState.map { it.uppercase() } shouldEqual "TEXAS WINS!".success()
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

    private fun productFound(product: Product) = product.success()
    private fun productLoading(): UiState<Product, Loading, Error> = Loading.loading()
    private fun productNotFound(): UiState<Product, Loading, Error> = Error.error()

    @Test
    fun `example with Product as success`() {
        val result = productFound(Product("Shirt")).fold(
            ifSuccess = { it.name },
            ifLoading = { "Loading" },
            ifError = { "Not found" },
        )

        result shouldEqual "Shirt"
    }

    @Test
    fun `example with Product as loading`() {
        val result = productLoading().fold(
            ifSuccess = { it.name },
            ifLoading = { "Loading" },
            ifError = { "Not found" },
        )

        result shouldEqual "Loading"
    }

    @Test
    fun `example with Product as error`() {
        val result = productNotFound().fold(
            ifSuccess = { it.name },
            ifLoading = { "Loading" },
            ifError = { "Not found" },
        )

        result shouldEqual "Not found"
    }
}
