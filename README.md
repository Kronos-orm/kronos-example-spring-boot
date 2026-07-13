# kronos-example-spring-boot

English | [简体中文](./README-zh_CN.md)

This repository now contains two variants of the same sample project (Spring Boot + Kronos ORM + Kotlin):

- maven-project: Maven build with the Kronos Maven compiler plugin.
- gradle-project: Gradle Kotlin DSL build with the Kronos Gradle compiler plugin.

Project layout:
- ./maven-project: Maven build (pom.xml) with Kotlin and Kronos compiler plugin configured.
- ./gradle-project: Gradle Kotlin DSL build (build.gradle.kts) with the equivalent dependencies configured.

Quick start

Backend (choose one):
- Maven (in maven-project):
  - Build: mvn -q -DskipTests package
  - Run (API only): mvn spring-boot:run
- Gradle (in gradle-project):
  - Run: gradle bootRun (or ./gradlew bootRun)

Frontend (Vue at ./vue-project):
- cd vue-project && npm install && npm run dev
- Open http://localhost:5173

Requirements
- JDK 17+
- MySQL running locally if you want to use the provided datasource config. You can override it with `MYSQL_JDBC_URL`, `MYSQL_USERNAME`, and `MYSQL_PASSWORD`.

Notes
- Both variants use Kotlin 2.4.0 and Kronos 0.2.3.
- The Maven variant uses `kronos-maven-plugin`; the Gradle variant uses `com.kotlinorm.kronos-gradle-plugin`.

For more about Kronos, see https://www.kotlinorm.com/
