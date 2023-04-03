package com.bsstokes.acme.domain.response

import com.bsstokes.acme.test.assertNull
import com.bsstokes.acme.test.shouldBeSameInstanceAs
import com.bsstokes.acme.test.shouldEqual
import org.junit.Test

class ResponseTest {

    @Test
    fun `isSuccess is only true for success`() {
        Unit.success().isSuccess shouldEqual true
        Unit.success().isError shouldEqual false
    }

    @Test
    fun `isError is only true for error`() {
        Unit.error().isSuccess shouldEqual false
        Unit.error().isError shouldEqual true
    }

    @Test
    fun `fold with Success`() {
        var ifSuccessCalled: Int? = null
        var ifErrorCalled: String? = null

        val success: Response<Int, String> = Response.Success(1)
        val result = success.fold(
            ifSuccess = { ifSuccessCalled = it; it },
            ifError = { ifErrorCalled = it;it },
        )

        ifSuccessCalled shouldEqual 1
        ifErrorCalled.assertNull()
        result shouldEqual 1
    }

    @Test
    fun `fold with Error`() {
        var ifSuccessCalled: Int? = null
        var ifErrorCalled: String? = null

        val error: Response<Int, String> = Response.Error("two")
        val result = error.fold(
            ifSuccess = { ifSuccessCalled = it;it },
            ifError = { ifErrorCalled = it;it },
        )

        ifSuccessCalled.assertNull()
        ifErrorCalled shouldEqual "two"
        result shouldEqual "two"
    }

    @Test
    fun `onSuccess is called on Success`() {
        var onSuccessCalled: Int? = null

        val success: Response<Int, String> = Response.Success(123)
        val result = success.onSuccess { onSuccessCalled = it }

        onSuccessCalled shouldEqual 123
        result shouldBeSameInstanceAs success
    }

    @Test
    fun `onSuccess is not called on Error`() {
        var onSuccessCalled: Int? = null

        val error: Response<Int, String> = Response.Error("nope")
        val result = error.onSuccess { onSuccessCalled = it }

        onSuccessCalled.assertNull()
        result shouldBeSameInstanceAs error
    }

    @Test
    fun `onError is not called on Success`() {
        var onErrorCalled: String? = null

        val success: Response<Int, String> = Response.Success(123)
        val result = success.onError { onErrorCalled = it }

        onErrorCalled.assertNull()
        result shouldBeSameInstanceAs success
    }

    @Test
    fun `onError is called on Error`() {
        var onErrorCalled: String? = null

        val error: Response<Int, String> = Response.Error("nope")
        val result = error.onError { onErrorCalled = it }

        onErrorCalled shouldEqual "nope"
        result shouldBeSameInstanceAs error
    }

    @Test
    fun `success extension creates a Success`() {
        Unit.success() shouldEqual Response.Success(Unit)
        1.success() shouldEqual Response.Success(1)
        "two".success() shouldEqual Response.Success("two")
    }

    @Test
    fun `error extension creates an Error`() {
        Unit.error() shouldEqual Response.Error(Unit)
        1.error() shouldEqual Response.Error(1)
        "oops".error() shouldEqual Response.Error("oops")
    }

    @Test
    fun `successOrElse returns success when non-null`() {
        100.successOrElse("oops!".error()) shouldEqual 100.success()
    }

    @Test
    fun `successOrElse returns error when null`() {
        null.successOrElse("oops!".error()) shouldEqual "oops!".error()
    }

    @Test
    fun `successOrElse with non-Response value returns success when non-null`() {
        100.successOrElse("oops!") shouldEqual 100.success()
    }

    @Test
    fun `successOrElse with non-Response value returns error when null`() {
        null.successOrElse("oops!") shouldEqual "oops!".error()
    }

    @Test
    fun `successOrErrorResponse returns success when non-null`() {
        "howdy".successOrErrorResponse() shouldEqual "howdy".success()
    }

    @Test
    fun `successOrErrorResponse returns UnspecifiedError when null`() {
        null.successOrErrorResponse() shouldEqual ErrorResponse
    }

    @Test
    fun `getOrNull returns values on Success`() {
        "success!".success().getOrNull() shouldEqual "success!"
    }

    @Test
    fun `getOrNull returns null on Error`() {
        "success!".error().getOrNull().assertNull()
    }

    @Test
    fun `map returns transformed Success on Success`() {
        val response: Response<String, Boolean> = "map".success()
        response.map { it.count() } shouldEqual 3.success()
    }

    @Test
    fun `map returns Error on Error`() {
        val response: Response<String, Boolean> = true.error()
        response.map { it.count() } shouldEqual true.error()
    }
}
