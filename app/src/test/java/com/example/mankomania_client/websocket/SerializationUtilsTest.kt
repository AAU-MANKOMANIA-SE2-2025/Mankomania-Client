package com.example.mankomania_client.websocket

import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * # SampleData
 *
 * A sample data class used to test JSON serialization and deserialization.
 *
 * @property id The identifier.
 * @property name The name field.
 *
 * @author
 * @since
 * @description This data class is annotated with @Serializable so that it can be used by JsonSerializer.
 */
@Serializable
data class SampleData(val id: Int, val name: String)

/**
 * # JsonSerializerTest
 *
 * This test class verifies the functionality of the JsonSerializer.
 * It tests that objects can be correctly serialized to JSON and deserialized back.
 *
 * @author
 * @since
 * @description Ensures that the JsonSerializer converts objects to JSON strings and back accurately.
 */
class JsonSerializerTest {

    /**
     * Tests the serialization and deserialization process using a sample data class.
     */
    @Test
    fun testSerializationAndDeserialization() {
        // Create a sample data object.
        val originalData = SampleData(id = 1, name = "Test")

        // Serialize the object to a JSON string.
        val jsonString = JsonSerializer.toJson(originalData)

        // Deserialize the JSON string back to an object.
        val deserializedData = JsonSerializer.fromJson<SampleData>(jsonString)

        // Assert that the deserialized object matches the original.
        assertEquals(originalData, deserializedData, "The deserialized object should match the original.")
    }
}
