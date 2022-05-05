package com.digital_enabling.android_mediator_sdk.registrationController

import com.digital_enabling.android_aries_sdk.agents.abstractions.IAgentContext
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionRecord

interface IRegistrationController {
    suspend fun addDevice(
        agentContext: IAgentContext,
        mediatorConnection: ConnectionRecord
    )
}