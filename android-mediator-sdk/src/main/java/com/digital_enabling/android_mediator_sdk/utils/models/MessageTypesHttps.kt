package com.digital_enabling.android_mediator_sdk.utils.models

object MessageTypesHttps {
    const val CREATE_INBOX: String = "https://didcomm.org/basic-routing/1.0/create-inbox"
    const val ADD_ROUTE: String = "https://didcomm.org/basic-routing/1.0/add-route"
    const val GET_INBOX_ITEMS: String = "https://didcomm.org/basic-routing/1.0/get-inbox-items"
    const val DELETE_INBOX_ITEMS: String = "https://didcomm.org/basic-routing/1.0/delete-inbox-items"
    const val ADD_DEVICE: String = "https://didcomm.org/basic-routing/1.0/add-device-info"
    const val CREATE_INBOX_RESPONSE: String = "https://didcomm.org/basic-routing/1.0/basic-routing/1.0/create-inbox-response"
    const val GET_INBOX_ITEMS_RESPONSE: String = "https://didcomm.org/basic-routing/1.0/basic-routing/1.0/get-inbox-items-response"
    const val INBOX_ITEM: String = "https://didcomm.org/basic-routing/1.0/basic-routing/1.0/inbox-item"
}