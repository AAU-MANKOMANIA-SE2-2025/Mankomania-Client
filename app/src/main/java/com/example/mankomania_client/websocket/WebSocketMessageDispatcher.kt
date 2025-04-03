package com.example.mankomania_client.websocket

class MessageDispatcher {

    // Map to store handlers for each message type.
    private val handlers = mutableMapOf<String, (String) -> Unit>()

    /**
     * Registers a handler for a specific message type.
     *
     * @param messageType The type of message that the handler will process.
     * @param handler Lambda function to process the message content.
     *
     * @author
     * @since
     * @description Associates a message type with a handler.
     */
    fun registerHandler(messageType: String, handler: (String) -> Unit) {
        handlers[messageType] = handler
    }

    /**
     * Dispatches a message to the appropriate registered handler.
     * The message should be in the format "type:content".
     *
     * @param message The message string to be dispatched.
     *
     * @author
     * @since
     * @description Splits the message by ":" and calls the corresponding handler, if one exists.
     */
    fun dispatch(message: String) {
        val parts = message.split(":", limit = 2)
        if (parts.size < 2) return // Ignore invalid message formats.
        val type = parts[0]
        val content = parts[1]
        handlers[type]?.invoke(content)
    }
}