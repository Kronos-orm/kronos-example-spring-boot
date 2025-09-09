package com.kotlinorm

import com.kotlinorm.Kronos.dataSource
import com.kotlinorm.common.DataSourceConfig
import com.kotlinorm.enums.KLoggerType
import com.kotlinorm.kronosSpringDemo.util.JsonResolverUtil
import com.kotlinorm.orm.ddl.table
import com.kotlinorm.pojo.User
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(
	scanBasePackages = [
        "com.kotlinorm.util",
        "com.kotlinorm.common",
        "com.kotlinorm.api",
	],
	exclude = [DataSourceAutoConfiguration::class]
)
@EnableScheduling
class KronosSpringDemoApplication(
	@Autowired val dataSourceConfig: DataSourceConfig
) {

    val wrapper by lazy { KronosBasicWrapper(dataSourceConfig.dataSource()) }

	@PostConstruct
    fun init() {
        Kronos.init {
            dataSource = { wrapper }
            fieldNamingStrategy = lineHumpNamingStrategy
            tableNamingStrategy = lineHumpNamingStrategy
            loggerType = KLoggerType.SLF4J_LOGGER
            serializeProcessor = JsonResolverUtil
        }
    }
}

fun main(args: Array<String>) {
    runApplication<KronosSpringDemoApplication>(*args)
    sync()
}

fun sync() {
    dataSource.table.syncTable<User>()
}
