package com.digital_enabling.android_mediator_sdk.registrationController

import com.digital_enabling.android_aries_sdk.agents.abstractions.IAgentContext
import com.digital_enabling.android_aries_sdk.didexchange.models.ConnectionRecord
import com.digital_enabling.android_aries_sdk.messagedispatcher.IMessageService
import com.digital_enabling.android_mediator_sdk.utils.messages.AddDeviceInfoMessage
import com.digital_enabling.android_mediator_sdk.utils.models.RequestType
import java.util.*
import kotlin.collections.HashMap

class RegistrationController(private val messageService: IMessageService) :
    IRegistrationController {
    override suspend fun addDevice(
        agentContext: IAgentContext,
        mediatorConnection: ConnectionRecord
    ) {
        val deviceId = UUID.randomUUID().toString()
        val deviceVendor = ""
        val deviceMetadata = HashMap<String, String>()
        deviceMetadata["Push"] = "Polling"
        deviceMetadata["CreatedAt"] = (System.currentTimeMillis() / 1000L).toString()
        val deviceInfoMessage = AddDeviceInfoMessage(agentContext.useMessageTypesHttps)
        deviceInfoMessage.deviceId = deviceId
        deviceInfoMessage.deviceMetadata = deviceMetadata
        deviceInfoMessage.deviceVendor = deviceVendor

        messageService.send(
            agentContext, deviceInfoMessage, mediatorConnection, listOf(
                RequestType.DEVICE_REGISTRATION.headerPair
            )
        )
    }
}