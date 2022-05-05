package com.digital_enabling.android_mediator_sdk.utilsTests.messages

import com.digital_enabling.android_mediator_sdk.utils.messages.InboxItemMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class InboxItemMessageTests {
    @Test
    @DisplayName("Serialization of InboxMessageItem works.")
    fun serialization_works() {
        //Arrange
        val testObject = InboxItemMessage()
        testObject.data = "testData"
        testObject.timeStamp = 10

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("Data"))
        Assertions.assertTrue(serializedObject.containsKey("Timestamp"))
    }
}