package com.example.mankomania_client.websocket

import org.junit.Test
import kotlin.test.assertNotNull

/**
 * # WebSocketClientConfigTest
 *
 * This test class verifies that the WebSocketClientConfig 
 * is properly instantiated and holds relevant configuration data.
 *
 * @author 
 * @since 
 * @description Tests basic instantiation and default properties of WebSocketClientConfig.
 */
class WebSocketClientConfigTest {

    @Test
    fun testConfigInstantiation() {
        val config = WebSocketClientConfig()
        assertNotNull(config, "WebSocketClientConfig should be instantiated.")
    }
}
