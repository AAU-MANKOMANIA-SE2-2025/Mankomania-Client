package com.example.mankomania_client

import org.junit.Assert.assertEquals
import org.junit.Test

class LogicSonarTest {
    @Test
    fun testMultiply() {
        val result = LogicSonar.multiply(6, 7)
        assertEquals(42, result)
    }
}