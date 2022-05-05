package com.digital_enabling.android_mediator_sdk.utilsTests.messages

import com.digital_enabling.android_mediator_sdk.utils.messages.AddDeviceInfoMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AddDeviceInfoMessageTests {

    @Test
    @DisplayName("Serialization of AddDeviceInfoMessage works.")
    fun serialization_works() {
        //Arrange
        val testObject = AddDeviceInfoMessage()
        testObject.deviceId = "testId"
        testObject.deviceVendor = "testVendor"
        testObject.deviceMetadata = HashMap()
        testObject.id = "testId"
        testObject.type = "testType"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("DeviceId"))
        Assertions.assertTrue(serializedObject.containsKey("DeviceVendor"))
        Assertions.assertTrue(serializedObject.containsKey("DeviceMetadata"))
    }

    @Test
    @DisplayName("Constructor of AddDeviceInfoMessage works.")
    fun constructor_works() {
        //Arrange
        val testObject = AddDeviceInfoMessage(true)
        testObject.deviceId = "deviceTestId"
        testObject.deviceVendor = "testVendor"
        testObject.deviceMetadata = HashMap()
        testObject.id = "testId"
        testObject.type = "testType"

        //Act
        val serializedObject = Json.encodeToJsonElement(testObject) as JsonObject

        //Assert
        Assertions.assertTrue(serializedObject.containsKey("DeviceId"))
        Assertions.assertTrue(serializedObject.containsKey("DeviceVendor"))
        Assertions.assertTrue(serializedObject.containsKey("DeviceMetadata"))
    }
}