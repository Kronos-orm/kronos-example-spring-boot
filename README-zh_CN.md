# kronos-example-spring-boot

[English](./README.md) | 简体中文

该仓库包含两套等价的示例工程（Spring Boot + Kronos ORM + Kotlin）：

- maven-project：Maven 构建，使用 Kronos Maven 编译器插件。
- gradle-project：Gradle Kotlin DSL 构建，使用 Kronos Gradle 编译器插件。

项目结构：
- ./maven-project：Maven 构建（pom.xml），已配置 Kotlin 与 Kronos 编译器插件。
- ./gradle-project：Gradle Kotlin DSL 构建（build.gradle.kts），已配置等价的依赖。

快速开始

后端（任选其一）：
- Maven（进入 maven-project 目录）：
  - 构建：mvn -q -DskipTests package
  - 运行（仅提供 API）：mvn spring-boot:run
- Gradle（进入 gradle-project 目录）：
  - 运行：gradle bootRun（或 ./gradlew bootRun）

前端（Vue 位于 ./vue-project）：
- cd vue-project && npm install && npm run dev
- 打开 http://localhost:5173

环境要求
- 需要 JDK 17+
- 如需使用提供的数据源配置，请在本地启动 MySQL。也可以通过 `MYSQL_JDBC_URL`、`MYSQL_USERNAME` 和 `MYSQL_PASSWORD` 覆盖连接信息。

说明
- 两套工程都使用 Kotlin 2.4.0 和 Kronos 0.2.3。
- Maven 版本使用 `kronos-maven-plugin`，Gradle 版本使用 `com.kotlinorm.kronos-gradle-plugin`。

更多 Kronos 信息参见：https://www.kotlinorm.com/

## Spring 事务

两套示例都注册了 `SpringKronosDataSourceWrapper`。查询和写入仍由内置 `KronosJdbcWrapper` 执行，JDBC 事务则由 Spring 持有。直接依赖 `spring-jdbc` 仅用于管理 `@Transactional` 的连接生命周期；示例不使用 Spring Data JDBC、`JdbcTemplate` 或第二套 SQL 执行层。

Gradle 和 Maven 工程中均包含以下文件：

- `common/SpringKronosDataSourceWrapper.kt`：桥接 Spring 绑定的连接，并将所有数据库操作委托给 Kronos。
- `common/DataSourceConfig.kt`：创建同一个 `DataSource`、对应的事务管理器和 Kronos wrapper。
- `transaction/TransactionExampleService.kt`：通过 Spring 代理的公开 service 方法演示提交、回滚和嵌套 `Kronos.transact`。
- `SpringTransactionIntegrationTests.kt`：只使用 Kronos 数据库操作验证上述行为。

事务管理器和 wrapper 必须使用同一个底层 `DataSource`。不要把 Spring 的原始 `DataSource` 直接传给 `KronosJdbcWrapper`，并假定它会自动加入 `@Transactional`。
