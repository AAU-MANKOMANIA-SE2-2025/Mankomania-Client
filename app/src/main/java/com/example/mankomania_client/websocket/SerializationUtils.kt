package com.example.mankomania_client.websocket

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * # JsonSerializer
 *
 * A fresh, standalone utility object for JSON serialization and deserialization
 * using kotlinx.serialization.
 *
 * @author
 * @since
 * @description Provides simple, reusable methods to convert objects to JSON strings and back.
 */
object JsonSerializer {

    // Configure a Json instance with custom settings.
    val json = Json {
        isLenient = true
        prettyPrint = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    /**
     * Serializes the given object into a JSON string.
     *
     * @param T The type of the object to be serialized.
     * @param obj The object instance to serialize.
     * @return A JSON string representing the object.
     *
     * @author
     * @since
     * @description Uses kotlinx.serialization to encode the object.
     */
    inline fun <reified T> toJson(obj: T): String {
        return json.encodeToString(obj)
    }

    /**
     * Deserializes the provided JSON string into an object of type T.
     *
     * @param T The target type.
     * @param jsonString The JSON string to deserialize.
     * @return An object of type T.
     *
     * @author
     * @since
     * @description Uses kotlinx.serialization to decode the JSON string.
     */
    inline fun <reified T> fromJson(jsonString: String): T {
        return json.decodeFromString(jsonString)
    }
}
