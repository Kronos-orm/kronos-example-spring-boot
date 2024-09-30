package com.kotlinorm.kronosSpringDemo

import com.kotlinorm.Kronos
import com.kotlinorm.kronosSpringDemo.common.DataSourceConfig
import com.kotlinorm.kronosSpringDemo.common.SpringDataWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(
    scanBasePackages = [
        "com.kotlinorm.kronosSpringDemo.common",
        "com.kotlinorm.kronosSpringDemo.controller"
    ], exclude = [DataSourceAutoConfiguration::class]
)
open class KronosSpringDemoApplication(
    @Autowired dataSourceConfig: DataSourceConfig
) {
    init {
        Kronos.dataSource = { SpringDataWrapper(dataSourceConfig.dataSource()) }
    }
}

fun main(args: Array<String>) {
    runApplication<KronosSpringDemoApplication>(*args)
}
