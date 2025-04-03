package com.example.mankomania_client.websocket

import kotlinx.serialization.Serializable

@Serializable
data class DummyData(val id: Int, val name: String)