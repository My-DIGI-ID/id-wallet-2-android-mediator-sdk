package com.digital_enabling.android_mediator_sdk.discoveryController

import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionInvitationMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AgentPublicConfiguration {
    @SerialName("ServiceEndpoint")
    lateinit var serviceEndpoint: String

    @SerialName("RoutingKey")
    lateinit var routingKey: String

    @SerialName("Invitation")
    lateinit var invitation: ConnectionInvitationMessage
}