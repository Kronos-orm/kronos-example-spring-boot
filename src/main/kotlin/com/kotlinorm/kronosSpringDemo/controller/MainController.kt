package com.kotlinorm.kronosSpringDemo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MainController {
    @GetMapping("/")
    fun main(): String {
        val user = MysqlUser()
        val columns = user.kronosColumns()
        return "${user.kronosTableName()}(${columns.joinToString(",")})"
    }
}