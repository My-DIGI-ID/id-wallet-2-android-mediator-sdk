package com.digital_enabling.android_mediator_sdk.utilsTests.messages

import com.digital_enabling.android_mediator_sdk.utils.messages.GetInboxItemsMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GetInboxItemsMessageTests {
    @Test
    @DisplayName("Serialization of GetInboxItemsMessage works.")
    fun serialization_works() {
        //Arrange
        val testObject = GetInboxItemsMessage()
        testObject.id = "testId"
        testObject.type = "testType"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("@id"))
    }
}