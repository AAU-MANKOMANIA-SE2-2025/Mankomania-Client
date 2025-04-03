package com.example.mankomania_client.websocket

import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Objekt für JSON-Serialisierung und -Deserialisierung mittels kotlinx.serialization.
 */
object JsonSerializer {

    // Konfiguriertes Json-Objekt mit benutzerdefinierten Einstellungen.
    val json: Json = Json {
        isLenient = true
        prettyPrint = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    /**
     * Serialisiert ein Objekt in einen JSON-String.
     *
     * @param T Der Typ des zu serialisierenden Objekts.
     * @param obj Das zu serialisierende Objekt.
     * @return Der JSON-String, der das Objekt repräsentiert.
     */
    inline fun <reified T> toJson(obj: T): String = json.encodeToString(obj)

    /**
     * Deserialisiert einen JSON-String in ein Objekt des Typs T.
     *
     * @param T Der Zieltyp.
     * @param jsonString Der JSON-String.
     * @return Das deserialisierte Objekt.
     */
    inline fun <reified T> fromJson(jsonString: String): T = json.decodeFromString(jsonString)
}
