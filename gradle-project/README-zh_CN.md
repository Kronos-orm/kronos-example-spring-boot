# kronos-example-spring-boot（Gradle）

[English](./README.md) | 简体中文

这是该项目的 Gradle 版本，基于 Spring Boot + Kronos ORM + Kotlin。

构建与运行（Gradle）：

- 使用本地 Gradle：gradle bootRun
- 使用 Gradle Wrapper（若添加）：./gradlew bootRun

本模块现在仅提供 REST API（不再提供服务端页面）。
前端（Vue）位于 ../vue-project：
- cd ../vue-project && npm install && npm run dev
- 然后打开 http://localhost:5173

说明：
- 需要 JDK 17+
- Kotlin、Spring Boot 与 Kronos 版本在 gradle.properties 中定义
- 已启用 Sonatype Snapshots 仓库以获取 Kronos 快照依赖
- Kronos 在 Maven 中使用了编译期插件，Gradle 版本如需编译期特性，请额外配置相应的 Kronos 编译器插件。
