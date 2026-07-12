package com.kotlinorm.kronosSpringDemo.util

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.toJSONString
import com.kotlinorm.interfaces.KronosSerializeProcessor
import kotlin.reflect.KClass
import kotlin.reflect.KType

object JsonResolverUtil : KronosSerializeProcessor {
    override fun serialize(obj: Any, kType: KType): String {
        return obj.toJSONString()
    }

    override fun deserialize(serializedStr: String, kType: KType): Any {
        val kClass = kType.classifier as? KClass<*> ?: return JSON.parse(serializedStr)
        return JSON.parseObject(serializedStr, kClass.java)
    }
}
