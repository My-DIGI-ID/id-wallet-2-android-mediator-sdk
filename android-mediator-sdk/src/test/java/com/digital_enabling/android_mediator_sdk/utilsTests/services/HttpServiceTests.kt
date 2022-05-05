package com.digital_enabling.android_mediator_sdk.utilsTests.services

import com.digital_enabling.android_mediator_sdk.utils.services.HttpService
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HttpServiceTests {
    @Test
    @DisplayName("HttpService get works if the call does not throw an exception.")
    fun get_works(): Unit = runBlocking {
        //Arrange
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel("testContent"),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val testClient = HttpClient(mockEngine)
        val testObject = HttpService(testClient)
        val testUrlString = "testUrlString"

        //Act
        val actual = testObject.get(testUrlString)

        //Assert
        Assertions.assertEquals("testContent", actual)
    }

    @Test
    @DisplayName("HttpService get throws an exception if the call throws an exception.")
    fun get_exception(): Unit = runBlocking {
        //Arrange
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel("testContent"),
                status = HttpStatusCode.BadGateway,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val testClient = HttpClient(mockEngine)
        val testObject = HttpService(testClient)
        val testUrlString = "testUrlString"

        //Act

        //Assert
        assertThrows<Exception> { testObject.get(testUrlString) }
    }
}