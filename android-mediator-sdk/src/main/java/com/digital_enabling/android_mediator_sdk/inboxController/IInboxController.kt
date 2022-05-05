package com.digital_enabling.android_mediator_sdk.inboxController

import com.digital_enabling.android_aries_sdk.agents.abstractions.IAgentContext
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionRecord
import com.digital_enabling.android_mediator_sdk.utils.messages.InboxItemMessage

interface IInboxController {
    suspend fun createInbox(
        mobileSecret: String,
        agentContext: IAgentContext,
        connectionRecord: ConnectionRecord,
        deviceValidationResult: String,
        deviceVendor: String
    ): String

    suspend fun deleteInboxItem(
        agentContext: IAgentContext,
        inboxItemIds: List<String>,
        connectionRecord: ConnectionRecord,
    )

    suspend fun getInboxItems(
        agentContext: IAgentContext,
        connectionRecord: ConnectionRecord
    ): List<InboxItemMessage>?
}