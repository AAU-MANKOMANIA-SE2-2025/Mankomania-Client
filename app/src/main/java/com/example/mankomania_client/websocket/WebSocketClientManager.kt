package com.example.mankomania_client.websocket

import android.util.Log
import kotlinx.coroutines.*
import okhttp3.*

/**
 * # WebSocketClientManager
 *
 * Manages the life cycle of the WebSocket connection. Provides methods to connect,
 * send messages, receive messages, and handle reconnection logic.
 *
 * @author
 * @since
 * @description Orchestrates all WebSocket operations, including reconnection handling.
 */
class WebSocketClientManager(private val config: WebSocketClientConfig = WebSocketClientConfig()) {

    private var webSocket: WebSocket? = null
    private val client: OkHttpClient = OkHttpClient()
    private var messageListener: ((String) -> Unit)? = null
    private var connected: Boolean = false

    /**
     * Initiates the WebSocket connection.
     *
     * @author
     * @since
     * @description Connects to the WebSocket server using the provided configuration.
     */
    fun connect() {
        val request = Request.Builder().url(config.serverUrl).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(ws: WebSocket, response: Response) {
                connected = true
                // Importiere android.util.Log, damit du Log-Ausgaben erhältst.
                Log.d("WebSocketClientManager", "Connected to server!")
            }

            override fun onMessage(ws: WebSocket, text: String) {
                Log.d("WebSocketClientManager", "Received message: $text")
                messageListener?.invoke(text)
            }

            override fun onFailure(ws: WebSocket, t: Throwable, response: Response?) {
                connected = false
                Log.e("WebSocketClientManager", "Connection failed: ${t.message}", t)
                // Attempt reconnection after the configured interval.
                GlobalScope.launch {
                    delay(config.reconnectInterval)
                    connect()
                }
            }

            override fun onClosed(ws: WebSocket, code: Int, reason: String) {
                connected = false
                Log.d("WebSocketClientManager", "Connection closed: code=$code, reason=$reason")
            }
        })
    }


    /**
     * Sends a message via the WebSocket connection.
     *
     * @param message The message to be sent.
     *
     * @author
     * @since
     * @description Sends the provided message to the connected WebSocket server.
     */
    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    /**
     * Closes the WebSocket connection.
     *
     * @author
     * @since
     * @description Closes the active WebSocket connection and shuts down resources.
     */
    fun close() {
        webSocket?.close(1000, "Closing connection")
        connected = false
        client.dispatcher.executorService.shutdown()
    }

    /**
     * Checks whether the WebSocket connection is active.
     *
     * @return true if connected, false otherwise.
     *
     * @author
     * @since
     * @description Provides the current connection status.
     */
    fun isConnected(): Boolean = connected

    /**
     * Sets a listener to handle incoming messages.
     *
     * @param onMessage Lambda function that receives messages.
     *
     * @author
     * @since
     * @description Allows external classes to register a custom message handler.
     */
    fun setOnMessageListener(onMessage: (String) -> Unit) {
        messageListener = onMessage
    }

    /**
     * Simulates a disconnection for testing purposes.
     *
     * @author
     * @since
     * @description Forcefully cancels the current WebSocket connection to simulate a disconnect.
     */
    fun simulateDisconnection() {
        webSocket?.cancel()
        connected = false
    }


}
