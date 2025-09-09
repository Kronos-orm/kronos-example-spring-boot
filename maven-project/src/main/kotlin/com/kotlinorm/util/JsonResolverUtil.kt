package com.kotlinorm.kronosSpringDemo.util

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.toJSONString
import com.kotlinorm.interfaces.KronosSerializeProcessor
import kotlin.reflect.KClass

object JsonResolverUtil : KronosSerializeProcessor {
    override fun serialize(obj: Any): String {
        return obj.toJSONString()
    }

    override fun deserialize(serializedStr: String, kClass: KClass<*>): Any {
        return JSON.parseObject(serializedStr, kClass.java)
    }
}
