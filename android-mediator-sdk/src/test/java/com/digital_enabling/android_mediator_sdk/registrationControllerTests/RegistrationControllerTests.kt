package com.digital_enabling.android_mediator_sdk.registrationControllerTests

import com.digital_enabling.android_aries_sdk.agents.abstractions.IAgentContext
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionRecord
import com.digital_enabling.android_aries_sdk.messagedispatcher.IMessageService
import com.digital_enabling.android_mediator_sdk.registrationController.RegistrationController
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class RegistrationControllerTests {
    private val mockMessageService = Mockito.mock(IMessageService::class.java)

    @Test
    @DisplayName("addDevice works")
    fun addDevice_works() {
        //Arrange
        val testObject = RegistrationController(mockMessageService)
        val testConnectionRecord = ConnectionRecord("testId")
        val mockAgentContext = Mockito.mock(IAgentContext::class.java)
        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel(""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        //Act

        //Assert
        Assertions.assertDoesNotThrow {
            runBlocking {
                launch {
                    testObject.addDevice(
                        mockAgentContext,
                        testConnectionRecord
                    )
                }
            }
        }
    }
}