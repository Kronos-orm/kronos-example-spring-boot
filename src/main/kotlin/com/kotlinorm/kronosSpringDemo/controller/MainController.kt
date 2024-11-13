package com.kotlinorm.kronosSpringDemo.controller

import com.kotlinorm.kronosSpringDemo.pojos.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MainController {
    @GetMapping("/")
    fun main(): Map<String, Any> {
        val user = User()
        return mapOf(
            "tableName" to user.kronosTableName(),
            "columns" to user.kronosColumns().map {
                mapOf(
                    "name" to it.name,
                    "type" to it.type,
                    "nullable" to it.nullable,
                    "primaryKey" to it.primaryKey.name,
                    "comment" to it.kDoc
                )
            }
        )
    }
}