package com.example.mankomania_client.websocket

/**
 * # WebSocketClientConfig
 *
 * This class holds the configuration parameters for the WebSocket connection.
 *
 * @author 
 * @since 
 * @description Defines basic parameters like the server endpoint and timeouts.
 */

class WebSocketClientConfig {


    val serverUrl: String = "ws://10.0.2.2:8080/ws"
    // Reconnect interval in milliseconds.
    val reconnectInterval: Long = 1000L
}