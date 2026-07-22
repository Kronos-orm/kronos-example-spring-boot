package com.kotlinorm

import com.kotlinorm.enums.KLoggerType
import com.kotlinorm.interfaces.KronosDataSourceWrapper
import com.kotlinorm.kronosSpringDemo.util.JsonResolverUtil
import com.kotlinorm.orm.ddl.table
import com.kotlinorm.pojo.User
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(
	scanBasePackages = [
        "com.kotlinorm.util",
	        "com.kotlinorm.common",
	        "com.kotlinorm.api",
	        "com.kotlinorm.transaction",
		]
)
@EnableScheduling
class KronosSpringDemoApplication(
	private val wrapper: KronosDataSourceWrapper
) {
	@PostConstruct
    fun init() {
        with(Kronos) {
            dataSource = { wrapper }
            fieldNamingStrategy = lineHumpNamingStrategy
            tableNamingStrategy = lineHumpNamingStrategy
            loggerType = KLoggerType.SLF4J_LOGGER
            registerValueCodec(JsonResolverUtil.codec)
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
