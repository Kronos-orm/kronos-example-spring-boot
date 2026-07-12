# kronos-example-spring-boot（Maven）

[English](./README.md) | 简体中文

这是该项目的 Maven 版本，基于 Spring Boot + Kronos ORM + Kotlin。

构建与运行（Maven）：

- 构建：mvn -q -DskipTests package
- 运行（仅提供 API，不再提供服务端模板）：mvn spring-boot:run

前端（Vue）位于 `../vue-project`：
- cd ../vue-project && npm install && npm run dev
- 然后打开 http://localhost:5173

说明：
- 需要 JDK 17+
- Kotlin 2.4.0 与 Kronos 0.2.1 在 `pom.xml` 中定义
- 使用 `kronos-maven-plugin` 提供 Kronos 编译期支持
