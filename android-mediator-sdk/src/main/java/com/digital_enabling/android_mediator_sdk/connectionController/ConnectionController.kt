package com.digital_enabling.android_mediator_sdk.connectionController

import com.digital_enabling.android_aries_sdk.agents.abstractions.IAgentContext
import com.digital_enabling.android_aries_sdk.agents.models.AgentEndpoint
import com.digital_enabling.android_aries_sdk.decorators.transport.ReturnRouteTypes
import com.digital_enabling.android_aries_sdk.didexchange.IConnectionService
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionRecord
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionRequestMessage
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionResponseMessage
import com.digital_enabling.android_aries_sdk.messagedispatcher.IMessageService
import com.digital_enabling.android_aries_sdk.messagedispatcher.sendReceiveExtended
import com.digital_enabling.android_aries_sdk.utils.RecordType
import com.digital_enabling.android_aries_sdk.wallet.IWalletRecordService
import com.digital_enabling.android_mediator_sdk.discoveryController.AgentPublicConfiguration
import com.digital_enabling.android_mediator_sdk.utils.messages.AddRouteMessage
import com.digital_enabling.android_mediator_sdk.utils.models.RequestType

class ConnectionController(
    private val connectionService: IConnectionService,
    private val walletRecordService: IWalletRecordService,
    private val messageService: IMessageService
) : IConnectionController {
    override suspend fun getConnectionResponseAsync(
        agentContext: IAgentContext,
        discovery: AgentPublicConfiguration
    ): String {
        //create connection request from invitation
        val request = connectionService.createRequest(agentContext, discovery.invitation)
        //send request to mediator

        val response =
            messageService.sendReceiveExtended<ConnectionRequestMessage, ConnectionResponseMessage>(
                agentContext,
                request.first,
                request.second,
                ReturnRouteTypes.ALL,
                listOf(RequestType.MEDIATOR_CONNECTION_REQUEST.headerPair)
            )
        //process mediator response
        return connectionService.processResponse(agentContext, response, request.second)
    }

    override suspend fun addRoute(
        agentContext: IAgentContext,
        connectionRecord: ConnectionRecord,
        routeDestination: String
    ) {
        val addRouteMessage = AddRouteMessage(agentContext.useMessageTypesHttps)
        addRouteMessage.routeDestination = routeDestination

        messageService.send(
            agentContext, addRouteMessage, connectionRecord, listOf(
                RequestType.ADD_ROUTING.headerPair
            )
        )
    }

    override suspend fun updateMediatorConnection(
        agentContext: IAgentContext,
        connectionRecordId: String
    ): ConnectionRecord {
        val wallet =
            agentContext.wallet ?: throw IllegalArgumentException("AgentContext has no wallet")

        val connectionRecord = walletRecordService.get<ConnectionRecord>(
            wallet,
            connectionRecordId,
            RecordType.CONNECTION_RECORD
        ) ?: throw Exception("Mediator connection not found")
        val endpoint = connectionRecord.endpoint
            ?: throw Exception("Mediator connection has no endpoint")

        val agentEndpoint = AgentEndpoint()
        try {
            agentEndpoint.uri = endpoint.uri
            agentEndpoint.did = null
            agentEndpoint.verkey = null
            connectionRecord.endpoint = agentEndpoint
        } catch (e: Exception) {
            throw Exception("Endpoint not set correctly.", e)
        }

        walletRecordService.update(wallet, connectionRecord)
        return connectionRecord
    }
}