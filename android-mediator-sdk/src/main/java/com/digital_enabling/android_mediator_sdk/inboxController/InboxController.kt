package com.digital_enabling.android_mediator_sdk.inboxController

import com.digital_enabling.android_aries_sdk.agents.abstractions.IAgentContext
import com.digital_enabling.android_aries_sdk.decorators.transport.ReturnRouteTypes
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionRecord
import com.digital_enabling.android_aries_sdk.messagedispatcher.IMessageService
import com.digital_enabling.android_aries_sdk.messagedispatcher.sendReceiveExtended
import com.digital_enabling.android_mediator_sdk.utils.messages.*
import com.digital_enabling.android_mediator_sdk.utils.models.RequestType
import java.util.*

class InboxController(
    private val messageService: IMessageService
) : IInboxController {
    override suspend fun createInbox(
        mobileSecret: String,
        agentContext: IAgentContext,
        connectionRecord: ConnectionRecord,
        deviceValidationResult: String,
        deviceVendor: String
    ): String {
        val metadataDictionary = HashMap<String, String>()
        metadataDictionary["Mobile-Secret"] = mobileSecret
        metadataDictionary["Device-Validation"] = deviceValidationResult
        metadataDictionary["Device-Vendor"] = deviceVendor
        val createInboxMessage = CreateInboxMessage(agentContext.useMessageTypesHttps)
        createInboxMessage.metadata = metadataDictionary

        val inboxResponse =
            messageService.sendReceiveExtended<CreateInboxMessage, CreateInboxResponseMessage>(
                agentContext,
                createInboxMessage,
                connectionRecord,
                ReturnRouteTypes.ALL,
                listOf(RequestType.INBOX_CREATION.headerPair)
            )
        if (!inboxResponse.inboxId.isNullOrEmpty()) {
            return inboxResponse.inboxId!!
        } else {
            throw Exception("Failed to create inbox at mediator")
        }
    }

    override suspend fun deleteInboxItem(
        agentContext: IAgentContext,
        inboxItemIds: List<String>,
        connectionRecord: ConnectionRecord
    ) {
        val deleteInboxMessage = DeleteInboxItemsMessage(agentContext.useMessageTypesHttps)
        deleteInboxMessage.inboxItemIds = inboxItemIds

        messageService.send(
            agentContext, deleteInboxMessage, connectionRecord, listOf(
                RequestType.DELETE_INBOX_ITEM.headerPair
            )
        )
    }

    override suspend fun getInboxItems(
        agentContext: IAgentContext,
        connectionRecord: ConnectionRecord
    ): List<InboxItemMessage>? {
        val getInboxItemsMessage = GetInboxItemsMessage(agentContext.useMessageTypesHttps)

        val inboxResponse =
            messageService.sendReceiveExtended<GetInboxItemsMessage, GetInboxItemsResponseMessage>(
                agentContext,
                getInboxItemsMessage,
                connectionRecord,
                ReturnRouteTypes.ALL,
                listOf(RequestType.GET_INBOX_ITEM.headerPair)
            )

        return inboxResponse.items
    }
}