package com.kotlinorm.kronosSpringDemo.util

import com.kotlinorm.enums.ValueCodecDirection
import com.kotlinorm.enums.ValueCodecOrigin
import com.kotlinorm.enums.ValueStorage
import com.kotlinorm.interfaces.ValueCodecContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.reflect.KType
import kotlin.reflect.typeOf

@OptIn(ExperimentalStdlibApi::class)
class JsonResolverUtilTest {
    private data class Preference(
        val name: String,
        val value: String
    )

    @Test
    fun `serialized codec stores JSON text and restores generic values`() {
        val preferences = listOf(Preference("theme", "dark"), Preference("locale", "zh-CN"))
        val type = typeOf<List<Preference>>()
        val encoded = JsonResolverUtil.codec.convert(preferences, encodeContext(type))

        assertTrue(encoded is String)
        assertEquals(preferences, JsonResolverUtil.codec.convert(encoded as String, decodeContext(type)))
    }

    private fun encodeContext(type: KType) = ValueCodecContext(
        direction = ValueCodecDirection.ENCODE,
        origin = ValueCodecOrigin.PARAMETER,
        sourceType = type,
        targetType = type,
        storage = ValueStorage.SERIALIZED
    )

    private fun decodeContext(type: KType) = ValueCodecContext(
        direction = ValueCodecDirection.DECODE,
        origin = ValueCodecOrigin.DATABASE,
        sourceType = typeOf<String>(),
        targetType = type,
        storage = ValueStorage.SERIALIZED
    )
}
