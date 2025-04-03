package com.example.mankomania_client.websocket



import kotlinx.coroutines.*
import kotlinx.serialization.Serializable

/**
 * # ServerCommunicationClient
 *
 * Demonstrates how to communicate with a server via WebSocket.
 * This example connects to the server, registers a message handler for responses,
 * and sends a test message to the server.
 *
 * @author
 * @since
 * @description Sample implementation for client-server communication.
 */
object ServerCommunicationClient {

    // Coroutine scope for asynchronous operations.
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    // Initialize WebSocket client manager (assumed to be implemented).
    private val webSocketManager = WebSocketClientManager()

    // Initialize a message dispatcher to route incoming messages.
    private val dispatcher = MessageDispatcher()

    init {
        // Forward any received message to the dispatcher.
        webSocketManager.setOnMessageListener { message ->
            dispatcher.dispatch(message)
        }
    }

    /**
     * Starts the communication by connecting to the server,
     * registering a message handler, and sending a test message.
     */
    fun start() {
        // Connect to the WebSocket server.
        webSocketManager.connect()

        // Register a handler for messages with type "response".
        dispatcher.registerHandler("response") { content ->
            println("Received server response: $content")
        }

        // Send a test message after a short delay to ensure connection is established.
        scope.launch {
            delay(1000)
            sendTestMessage()
        }
    }

    /**
     * Sends a test message to the server.
     */
    fun sendTestMessage() {
        // Create a test message with type "request".
        val testMessage = ClientMessage(type = "request", content = "Hello Server, this is a test message!")
        // Serialize the message to JSON.
        val jsonMessage = JsonSerializer.toJson(testMessage)
        // Send the JSON message over the WebSocket connection.
        webSocketManager.sendMessage(jsonMessage)
    }

    /**
     * Stops the communication by closing the WebSocket connection and cancelling tasks.
     */
    fun stop() {
        webSocketManager.close()
        scope.cancel()
    }
}

/**
 * # ClientMessage
 *
 * Represents a sample message sent from the client to the server.
 *
 * @property type The type of message (e.g., "request").
 * @property content The content of the message.
 *
 * @author
 * @since
 * @description This data class is serializable and used for JSON conversion.
 */
@Serializable
data class ClientMessage(val type: String, val content: String)
