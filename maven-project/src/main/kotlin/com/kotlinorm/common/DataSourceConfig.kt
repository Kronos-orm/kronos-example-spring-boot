package com.kotlinorm.common

import com.kotlinorm.interfaces.KronosDataSourceWrapper
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource() = BasicDataSource()

    @Bean
    fun transactionManager(dataSource: DataSource) = DataSourceTransactionManager(dataSource)

    @Bean
    fun kronosDataSourceWrapper(
        dataSource: DataSource,
        transactionManager: DataSourceTransactionManager
    ): KronosDataSourceWrapper = SpringKronosDataSourceWrapper(dataSource, transactionManager)
}
