package com.digital_enabling.android_mediator_sdk.utilsTests.messages

import com.digital_enabling.android_mediator_sdk.utils.messages.AddRouteMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AddRouteMessageTests {
    @Test
    @DisplayName("Serialization of AddRouteMessage works.")
    fun serialization_works() {
        //Arrange
        val testObject = AddRouteMessage()
        testObject.id = "testId"
        testObject.type = "testType"
        testObject.routeDestination = "testRoute"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("RouteDestination"))
    }

    @Test
    @DisplayName("Constructor of AddRouteMessage works.")
    fun constructor_works() {
        //Arrange
        val testObject = AddRouteMessage(true)
        testObject.routeDestination = "testRoute"
        testObject.id = "testId"
        testObject.type = "testType"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("RouteDestination"))
    }
}