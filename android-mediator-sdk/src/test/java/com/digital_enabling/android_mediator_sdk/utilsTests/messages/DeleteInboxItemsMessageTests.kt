package com.digital_enabling.android_mediator_sdk.utilsTests.messages

import com.digital_enabling.android_mediator_sdk.utils.messages.DeleteInboxItemsMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DeleteInboxItemsMessageTests {
    @Test
    @DisplayName("Serialization of DeleteInboxItemsMessage works.")
    fun serialization_works() {
        //Arrange
        val testObject = DeleteInboxItemsMessage()
        testObject.id = "testId"
        testObject.type = "testType"
        testObject.inboxItemIds = listOf("testDeleteId")

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("InboxItemIds"))
    }

    @Test
    @DisplayName("Constructor of DeleteInboxItemsMessage works.")
    fun constructor_works() {
        //Arrange
        val testObject = DeleteInboxItemsMessage(true)
        testObject.inboxItemIds = listOf("testDeleteId")
        testObject.id = "testId"
        testObject.type = "testType"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("InboxItemIds"))
    }
}