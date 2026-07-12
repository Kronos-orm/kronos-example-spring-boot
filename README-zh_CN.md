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
- 两套工程都使用 Kotlin 2.4.0 和 Kronos 0.2.1。
- Maven 版本使用 `kronos-maven-plugin`，Gradle 版本使用 `com.kotlinorm.kronos-gradle-plugin`。

更多 Kronos 信息参见：https://www.kotlinorm.com/
