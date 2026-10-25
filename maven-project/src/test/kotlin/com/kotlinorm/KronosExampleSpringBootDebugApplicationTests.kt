package com.kotlinorm

import com.kotlinorm.enums.KLoggerType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KronosExampleSpringBootDebugApplicationTests {

	@Test
	fun contextLoads() {
        assertEquals(KLoggerType.SLF4J_LOGGER, Kronos.logging.type)
    }

    @Test
    fun loggingSwitchPreservesTheInstalledSpringAdapter() {
        val originalEnabled = Kronos.logging.enabled
        val originalType = Kronos.logging.type
        val logger = Kronos.logging.logger("SpringLoggingProbe")
        try {
            Kronos.logging.enabled = true
            val backendEnabled = logger.isInfoEnabled()
            Kronos.logging.enabled = false
            assertFalse(logger.isInfoEnabled())
            Kronos.logging.enabled = true
            assertEquals(backendEnabled, logger.isInfoEnabled())
            assertEquals(originalType, Kronos.logging.type)
        } finally {
            Kronos.logging.enabled = originalEnabled
        }
    }

}
