package com.digital_enabling.android_mediator_sdk.discoveryControllerTests

import com.digital_enabling.android_aries_sdk.configuration.IProvisioningService
import com.digital_enabling.android_aries_sdk.configuration.ProvisioningRecord
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionInvitationMessage
import com.digital_enabling.android_aries_sdk.wallet.IWalletRecordService
import com.digital_enabling.android_mediator_sdk.discoveryController.AgentPublicConfiguration
import com.digital_enabling.android_mediator_sdk.discoveryController.DiscoveryController
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hyperledger.indy.sdk.wallet.Wallet
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.MockitoKotlinException
import org.mockito.kotlin.any
import java.net.URI

class DiscoveryControllerTests {
    private val mockWalletRecordService = mock(IWalletRecordService::class.java)
    private val mockProvisioningService = mock(IProvisioningService::class.java)
    private val mockWallet = mock(Wallet::class.java)

    @Test
    @DisplayName("discoverConfigurationAsync throws an exception when httpResponse can not be deserialized.")
    fun discoverConfigurationAsync_throwsException(): Unit = runBlocking {
        //Assign
        val testObject =
            DiscoveryController(mockProvisioningService, mockWalletRecordService)
        val testUri = URI("5")

        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel(""),
                status = HttpStatusCode.BadRequest,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        //Act

        //Assert
        assertThrows<Exception> { testObject.discoverConfigurationAsync(testUri) }
    }

    @Test
    @DisplayName("updateProvisioningRecordAsync works correctly with a proper AgentPublicConfiguration as input.")
    fun updateProvisioningRecordAsync_properAgentPublicConfiguration(): Unit = runBlocking {
        //Assign
        val testObject =
            DiscoveryController(mockProvisioningService, mockWalletRecordService)
        val testProvisioningRecord = ProvisioningRecord()
        val testInvitation = ConnectionInvitationMessage()
        val invitationString = Json.encodeToString(testInvitation)
        val testContent =
            "{\"ServiceEndpoint\":\"testServiceEndpoint\",\"RoutingKey\":\"testRoutingKey\",\"Invitation\":${invitationString}}"
        val testDiscovery = Json.decodeFromString<AgentPublicConfiguration>(testContent)

        `when`(
            mockProvisioningService.getProvisioning(any())
        ).thenReturn(testProvisioningRecord)

        `when`(
            mockWalletRecordService.update(any(), any())
        ).thenReturn(null)

        //Act
        val actual = testObject.updateProvisioningRecordAsync(mockWallet, testDiscovery)
        val expectedUri = "testServiceEndpoint"
        val expectedVerkey = arrayOf("testRoutingKey")

        //Assert
        Assertions.assertEquals(expectedUri, actual.endpoint.uri)
        Assertions.assertArrayEquals(expectedVerkey, actual.endpoint.verkey)
    }

    @Test
    @DisplayName("updateProvisioningRecordAsync throws an exception when no provisioningRecord is found.")
    fun updateProvisioningRecordAsync_throwsException_noProvisioningRecord(): Unit = runBlocking {
        //Assign
        val testObject =
            DiscoveryController(mockProvisioningService, mockWalletRecordService)
        val testInvitation = ConnectionInvitationMessage()
        val invitationString = Json.encodeToString(testInvitation)
        val testContent =
            "{\"ServiceEndpoint\":\"testServiceEndpoint\",\"RoutingKey\":\"testRoutingKey\",\"Invitation\":${invitationString}}"
        val testDiscovery = Json.decodeFromString<AgentPublicConfiguration>(testContent)

        `when`(
            mockProvisioningService.getProvisioning(any())
        ).thenThrow(MockitoKotlinException("", Exception()))

        //Act

        //Assert
        assertThrows<Exception> {
            testObject.updateProvisioningRecordAsync(
                mockWallet,
                testDiscovery
            )
        }
    }

    @Test
    @DisplayName("updateProvisioningRecordAsync throws an exception when ProvisioningRecord could not be updated.")
    fun updateProvisioningRecordAsync_throwsException_noProvisioningRecordUpdate(): Unit =
        runBlocking {
            //Assign
            val testObject =
                DiscoveryController(
                    mockProvisioningService,
                    mockWalletRecordService
                )
            val testProvisioningRecord = ProvisioningRecord()
            val testInvitation = ConnectionInvitationMessage()
            val invitationString = Json.encodeToString(testInvitation)
            val testContent =
                "{\"ServiceEndpoint\":\"testServiceEndpoint\",\"RoutingKey\":\"testRoutingKey\",\"Invitation\":${invitationString}}"
            val testDiscovery = Json.decodeFromString<AgentPublicConfiguration>(testContent)

            `when`(
                mockProvisioningService.getProvisioning(any())
            ).thenReturn(testProvisioningRecord)

            `when`(
                mockWalletRecordService.update(any(), any())
            ).thenThrow(MockitoKotlinException("", Exception()))

            //Act

            //Assert
            assertThrows<Exception> {
                testObject.updateProvisioningRecordAsync(
                    mockWallet,
                    testDiscovery
                )
            }
        }
}