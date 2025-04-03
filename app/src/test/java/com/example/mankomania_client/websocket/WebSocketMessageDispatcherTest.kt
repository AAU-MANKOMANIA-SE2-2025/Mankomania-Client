package com.example.mankomania_client.websocket

/**
 * # WebSocketMessageDispatcherTest
 *
 * Contains test cases for WebSocketMessageDispatcher.
 *
 * @author Lucy
 * @since Sprint 1
 * @description Why this file is needed:
 * This file is part of the 'websocket' package and is responsible for 
 * providing or testing a key aspect of our WebSocket-based communication logic.
 */

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * # MessageDispatcherTest
 *
 * This test class verifies the functionality of the MessageDispatcher.
 * It ensures that messages are correctly routed to the registered handlers.
 *
 * @author
 * @since
 * @description Tests the registration and dispatching logic of the MessageDispatcher.
 */
class MessageDispatcherTest {

    @Test
    fun testDispatchWithoutRegisteredHandler() {
        // Create an instance of MessageDispatcher.
        val dispatcher = MessageDispatcher()

        // Dispatch a message with an unregistered type.
        dispatcher.dispatch("unknown:Test")

        // No exception should occur and no handler should be invoked.
        assertTrue(true, "Dispatching a message with no registered handler should not cause errors.")
    }

    @Test
    fun testMultipleHandlers() {
        // Create an instance of MessageDispatcher.
        val dispatcher = MessageDispatcher()
        var greetingResult: String? = null
        var farewellResult: String? = null

        // Register handlers for "greeting" and "farewell" messages.
        dispatcher.registerHandler("greeting") { message ->
            greetingResult = "Hello, $message"
        }
        dispatcher.registerHandler("farewell") { message ->
            farewellResult = "Goodbye, $message"
        }

        // Dispatch messages for both handlers.
        dispatcher.dispatch("greeting:Alice")
        dispatcher.dispatch("farewell:Bob")

        // Verify that each handler processed its respective message.
        assertEquals("Hello, Alice", greetingResult, "Greeting handler should process the message correctly.")
        assertEquals("Goodbye, Bob", farewellResult, "Farewell handler should process the message correctly.")
    }
}
