package com.digital_enabling.android_mediator_sdk.utilsTests.messages

import com.digital_enabling.android_mediator_sdk.utils.messages.CreateInboxMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class CreateInboxMessageTests {

    @Test
    @DisplayName("Serialization of CreateInboxMessage works.")
    fun serialization_works() {
        //Arrange
        val testObject = CreateInboxMessage()
        testObject.metadata = HashMap()

        testObject.id = "testId"
        testObject.type = "testType"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("Metadata"))
    }

    @Test
    @DisplayName("Constructor of CreateInboxMessage works.")
    fun constructor_works() {
        //Arrange
        val testObject = CreateInboxMessage(true)
        testObject.metadata = HashMap()
        testObject.id = "testId"
        testObject.type = "testType"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("Metadata"))
    }
}