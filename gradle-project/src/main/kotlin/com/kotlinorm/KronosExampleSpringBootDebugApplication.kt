package com.kotlinorm

import com.kotlinorm.common.DataSourceConfig
import com.kotlinorm.enums.KLoggerType
import com.kotlinorm.kronosSpringDemo.util.JsonResolverUtil
import com.kotlinorm.orm.ddl.table
import com.kotlinorm.pojo.User
import com.kotlinorm.wrappers.KronosJdbcWrapper
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration
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
    @param:Autowired val dataSourceConfig: DataSourceConfig
) {

    val wrapper by lazy { KronosJdbcWrapper(dataSourceConfig.dataSource()) }

	@PostConstruct
    fun init() {
        with(Kronos) {
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
    Kronos.dataSource().table.syncTable<User>()
}
