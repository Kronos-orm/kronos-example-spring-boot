package com.kotlinorm.kronosSpringDemo

import com.kotlinorm.Kronos
import com.kotlinorm.kronosSpringDemo.controller.SpringDataWrapper
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(
    scanBasePackages = ["com.kotlinorm.kronosSpringDemo.controller"], exclude = [DataSourceAutoConfiguration::class]
)
open class KronosSpringDemoApplication

fun main(args: Array<String>) {
    Kronos.apply {
        dataSource = {
            SpringDataWrapper(BasicDataSource().apply {
                driverClassName = "com.mysql.cj.jdbc.Driver"
                url = "jdbc:mysql://localhost:3306/test"
                username = "root"
                password = "******"
                maxIdle = 10
            })
        }
    }
    runApplication<KronosSpringDemoApplication>(*args)
}
