package com.digital_enabling.android_mediator_sdk.connectionController

import com.digital_enabling.android_aries_sdk.agents.abstractions.IAgentContext
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionRecord
import com.digital_enabling.android_mediator_sdk.discoveryController.AgentPublicConfiguration

interface IConnectionController {

    suspend fun updateMediatorConnection(
        agentContext: IAgentContext,
        connectionRecordId: String
    ): ConnectionRecord

    suspend fun getConnectionResponseAsync(
        agentContext: IAgentContext,
        discovery: AgentPublicConfiguration
    ): String

    suspend fun addRoute(
        agentContext: IAgentContext,
        connectionRecord: ConnectionRecord,
        routeDestination: String
    )
}