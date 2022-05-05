package com.digital_enabling.android_mediator_sdk.utilsTests.messages

import com.digital_enabling.android_mediator_sdk.utils.messages.CreateInboxResponseMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CreateInboxResponseMessageTests {

    @Test
    @DisplayName("Serialization of CreateInboxResponseMessage works.")
    fun serialization_works() {
        //Arrange
        val testObject = CreateInboxResponseMessage()
        testObject.inboxId = "testInboxId"
        testObject.inboxKey = "testInboxKey"

        testObject.id = "testId"
        testObject.type = "testType"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("InboxId"))
        Assertions.assertTrue(serializedObject.containsKey("InboxKey"))
    }

    @Test
    @DisplayName("Constructor of CreateInboxResponseMessage works.")
    fun constructor_works() {
        //Arrange
        val testObject = CreateInboxResponseMessage(true)
        testObject.inboxId = "testInboxId"
        testObject.inboxKey = "testInboxKey"
        testObject.id = "testId"
        testObject.type = "testType"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("useMessageTypesHttps"))
    }
}