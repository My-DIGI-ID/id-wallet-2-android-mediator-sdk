package com.digital_enabling.android_mediator_sdk.utils.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InboxMessageItem {
    @SerialName("Data")
    var data: String? = null

    @SerialName("TimeStamp")
    var timeStamp: Long? = null
}
