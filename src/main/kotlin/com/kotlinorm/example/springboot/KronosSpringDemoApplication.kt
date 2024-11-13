package com.kotlinorm.example.springboot

import com.kotlinorm.Kronos
import com.kotlinorm.example.springboot.common.DataSourceConfig
import com.kotlinorm.example.springboot.common.SpringDataWrapper
import com.kotlinorm.example.springboot.common.SpringDataWrapper.Companion.wrap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(
    scanBasePackages = [
        "com.kotlinorm.example.springboot.common",
        "com.kotlinorm.example.springboot.controller"
    ], exclude = [DataSourceAutoConfiguration::class]
)
open class KronosSpringDemoApplication(
    @Autowired dataSourceConfig: DataSourceConfig
) {
    val dataSource: SpringDataWrapper by lazy { dataSourceConfig.dataSource().wrap() }
    init {
        Kronos.dataSource = { dataSource }
    }
}

fun main(args: Array<String>) {
    runApplication<KronosSpringDemoApplication>(*args)
}
