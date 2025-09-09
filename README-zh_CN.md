# kronos-example-spring-boot

[English](./README.md) | 简体中文

该仓库现在包含两套等价的示例工程（Spring Boot + Kronos ORM + Kotlin）：

- maven-project：基于 Maven 的项目（从根目录原工程复制）。
- gradle-project：基于 Gradle 的项目。

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
- 如需使用提供的数据源配置，请在本地启动 MySQL 并根据需要修改 application.yml

说明
- Kotlin 2.2.0 与 Kronos 0.0.6-SNAPSHOT 与原 pom.xml 保持一致。
- 因 Kronos 使用快照版本，已启用 Sonatype Snapshots 仓库。
- Maven 版本使用 kronos-maven-plugin 提供编译期支持。如需在 Gradle 中使用相同的编译期特性，请为 Gradle 单独配置 Kronos 编译器插件（本样例已包含运行时库与标准 Kotlin/Spring Boot 插件）。

更多 Kronos 信息参见：https://www.kotlinorm.com/
