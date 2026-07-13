# kronos-example-spring-boot（Gradle）

[English](./README.md) | 简体中文

这是该项目的 Gradle 版本，基于 Spring Boot + Kronos ORM + Kotlin。

构建与运行（Gradle）：

- 使用本地 Gradle：gradle bootRun
- 使用 Gradle Wrapper（若添加）：./gradlew bootRun

前端（Vue）位于 `../vue-project`：
- cd ../vue-project && npm install && npm run dev
- 然后打开 http://localhost:5173

说明：
- 需要 JDK 17+
- Kotlin、Spring Boot 与 Kronos 版本在 `gradle.properties` 和 `libs.versions.toml` 中定义
- 本示例使用 Kronos 0.2.3 和 `com.kotlinorm.kronos-gradle-plugin` 编译器插件
