package com.example.mankomania_client.websocket

import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Datenklasse für Testzwecke der JSON-Serialisierung.
 */
@Serializable
data class SampleData(val id: Int, val name: String)

/**
 * Testklasse zur Überprüfung der JSON-Serialisierungsfunktionen.
 */
class JsonSerializerTest {

    @Test
    fun testSerializationAndDeserialization() {
        val originalData = SampleData(id = 1, name = "Test")
        val jsonString = JsonSerializer.toJson(originalData)
        val deserializedData = JsonSerializer.fromJson<SampleData>(jsonString)
        assertEquals(originalData, deserializedData, "Die deserialisierten Daten sollten den ursprünglichen entsprechen.")
    }
}
