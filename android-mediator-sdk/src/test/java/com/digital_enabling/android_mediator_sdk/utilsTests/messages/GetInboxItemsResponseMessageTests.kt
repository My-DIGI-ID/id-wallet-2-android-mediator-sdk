package com.digital_enabling.android_mediator_sdk.utilsTests.messages

import com.digital_enabling.android_mediator_sdk.utils.messages.GetInboxItemsResponseMessage
import com.digital_enabling.android_mediator_sdk.utils.messages.InboxItemMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GetInboxItemsResponseMessageTests {
    @Test
    @DisplayName("Serialization of GetInboxItemsResponseMessage works.")
    fun serialization_works() {
        //Arrange
        val testObject = GetInboxItemsResponseMessage()
        testObject.id = "testId"
        testObject.type = "testType"
        val testMessageItem = InboxItemMessage()
        testMessageItem.data = "testData"
        testMessageItem.timeStamp = 10
        testObject.items = listOf(testMessageItem)

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("Items"))
    }
}