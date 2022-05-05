package com.digital_enabling.android_mediator_sdk.utilsTests

import com.digital_enabling.android_mediator_sdk.utils.RequestType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RequestTypeTests {
    @Test
    @DisplayName("MEDIATOR_CONNECTION_REQUEST has correct header.")
    fun mediator_connection_request_header() {
        //Arrange
        val testObject = RequestType.MEDIATOR_CONNECTION_REQUEST

        //Act

        //Assert
        Assertions.assertEquals("IsMediatorConnectionRequest", testObject.headerPair.first)
        Assertions.assertEquals("true", testObject.headerPair.second)
    }

    @Test
    @DisplayName("INBOX_CREATION has correct header.")
    fun inbox_creation_header() {
        //Arrange
        val testObject = RequestType.INBOX_CREATION

        //Act

        //Assert
        Assertions.assertEquals("IsInboxCreation", testObject.headerPair.first)
        Assertions.assertEquals("true", testObject.headerPair.second)
    }

    @Test
    @DisplayName("DEVICE_REGISTRATION has correct header.")
    fun device_registration_header() {
        //Arrange
        val testObject = RequestType.DEVICE_REGISTRATION

        //Act

        //Assert
        Assertions.assertEquals("IsDeviceRegistration", testObject.headerPair.first)
        Assertions.assertEquals("true", testObject.headerPair.second)
    }

    @Test
    @DisplayName("DELETE_INBOX_ITEM has correct header.")
    fun delete_inbox_item_header() {
        //Arrange
        val testObject = RequestType.DELETE_INBOX_ITEM

        //Act

        //Assert
        Assertions.assertEquals("IsDeleteInboxItem", testObject.headerPair.first)
        Assertions.assertEquals("true", testObject.headerPair.second)
    }

    @Test
    @DisplayName("GET_INBOX_ITEM has correct header.")
    fun get_inbox_item_header() {
        //Arrange
        val testObject = RequestType.GET_INBOX_ITEM

        //Act

        //Assert
        Assertions.assertEquals("IsGetInboxItems", testObject.headerPair.first)
        Assertions.assertEquals("true", testObject.headerPair.second)
    }

    @Test
    @DisplayName("ADD_ROUTING has correct header.")
    fun add_routing_header() {
        //Arrange
        val testObject = RequestType.ADD_ROUTING

        //Act

        //Assert
        Assertions.assertEquals("IsAddRouting", testObject.headerPair.first)
        Assertions.assertEquals("true", testObject.headerPair.second)
    }
}