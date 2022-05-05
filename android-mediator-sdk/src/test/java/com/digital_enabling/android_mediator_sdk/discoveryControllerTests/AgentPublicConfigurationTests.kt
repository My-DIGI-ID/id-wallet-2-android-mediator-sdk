package com.digital_enabling.android_mediator_sdk.discoveryControllerTests

import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionInvitationMessage
import com.digital_enabling.android_mediator_sdk.discoveryController.AgentPublicConfiguration
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AgentPublicConfigurationTests {

    @Test
    @DisplayName("successfully create an AgentPublicConfiguration")
    fun constructor_AgentPublicConfiguration(): Unit = runBlocking {
        //Assign

        //Act
        val testServiceEndpoint = "testServiceEndpoint"
        val testRoutingKey = "testRoutingKey"
        val testInvitation = ConnectionInvitationMessage()
        val testAgentPublicConfiguration = AgentPublicConfiguration()

        testAgentPublicConfiguration.serviceEndpoint = testServiceEndpoint
        testAgentPublicConfiguration.routingKey = testRoutingKey
        testAgentPublicConfiguration.invitation = testInvitation

        //Assert
        Assertions.assertEquals(testServiceEndpoint, testAgentPublicConfiguration.serviceEndpoint)
        Assertions.assertEquals(testRoutingKey, testAgentPublicConfiguration.routingKey)
    }

    @Test
    @DisplayName("AgentPublicConfiguration can be deserialized from HttpResponse.")
    fun deserialize_works() {
        val httpResponse =
            "{\"ServiceEndpoint\":\"https://mediator.dev.essid-demo.com\",\"RoutingKey\":\"A5k9RAU121Wr3gXtg6yZHnZTGbzs6DPQTa6Vn3Q1kSWP\",\"Invitation\":{\"label\":\"SSI-MEDIATION-AGENT\",\"imageUrl\":\"https://digital-enabling.eu/assets/images/logo.png\",\"serviceEndpoint\":\"https://mediator.dev.essid-demo.com\",\"routingKeys\":null,\"recipientKeys\":[\"DMd5kppGXbHFHHVGdYKs8FcfsMtgx9NL3agbGmVSPehc\"],\"@id\":\"5dafc4aa-1e23-46de-a393-b34901fde23b\",\"@type\":\"did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/connections/1.0/invitation\"}}"

        val actual = Json.decodeFromString<AgentPublicConfiguration>(httpResponse)

        Assertions.assertTrue(actual.routingKey == "A5k9RAU121Wr3gXtg6yZHnZTGbzs6DPQTa6Vn3Q1kSWP")
    }
}