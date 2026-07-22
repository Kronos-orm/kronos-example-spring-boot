package com.kotlinorm.kronosSpringDemo.util

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.toJSONString
import com.kotlinorm.interfaces.serializedValueCodec
import kotlin.reflect.jvm.javaType

object JsonResolverUtil {
    val codec = serializedValueCodec(
        encode = { value, _ -> value.toJSONString() },
        decode = { text, type -> JSON.parseObject(text, type.javaType) }
    )
}
