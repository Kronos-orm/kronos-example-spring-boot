package com.kotlinorm.common

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource() = BasicDataSource()
}
