package com.digital_enabling.android_mediator_sdk.utils.models

enum class RequestType(val headerPair: Pair<String, String>) {
    MEDIATOR_CONNECTION_REQUEST(Pair("IsMediatorConnectionRequest", "true")),
    INBOX_CREATION(Pair("IsInboxCreation", "true")),
    DEVICE_REGISTRATION(Pair("IsDeviceRegistration", "true")),
    DELETE_INBOX_ITEM(Pair("IsDeleteInboxItem", "true")),
    GET_INBOX_ITEM(Pair("IsGetInboxItems", "true")),
    ADD_ROUTING(Pair("IsAddRouting", "true"))
}