package com.digital_enabling.android_mediator_sdk.utils.messages

import com.digital_enabling.android_aries_sdk.agents.models.AgentMessage
import com.digital_enabling.android_aries_sdk.common.toByteArray
import com.digital_enabling.android_mediator_sdk.utils.models.MessageTypes
import com.digital_enabling.android_mediator_sdk.utils.models.MessageTypesHttps
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import java.util.*

@Serializable
class GetInboxItemsResponseMessage : AgentMessage {
    constructor(useMessageTypesHttps: Boolean = false) : super(useMessageTypesHttps) {
        id = UUID.randomUUID().toString()
        type =
            if (useMessageTypesHttps) MessageTypesHttps.GET_INBOX_ITEMS_RESPONSE else MessageTypes.GET_INBOX_ITEMS_RESPONSE
    }

    @SerialName("Items")
    var items: List<InboxItemMessage>? = null

    override fun toJsonObject(): JsonObject {
        val json = Json.encodeToJsonElement(this) as JsonObject
        val newMap = HashMap<String, JsonElement>()
        json.forEach { (key, value) ->
            newMap[key] = value
        }
        this.getDecorators().forEach { decorator ->
            val map = Json.decodeFromJsonElement<Map<String, JsonElement>>(decorator)
            map.forEach { (key, value) ->
                newMap[key] = value
            }
        }
        return JsonObject(newMap)
    }

    override fun asByteArray(): ByteArray {
        return this.toJsonObject().toByteArray()
    }
}