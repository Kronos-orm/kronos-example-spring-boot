# kronos-example-spring-boot

English | [简体中文](./README-zh_CN.md)

This repository now contains two variants of the same sample project (Spring Boot + Kronos ORM + Kotlin):

- maven-project: The original Maven-based project copied from the root.
- gradle-project: A Gradle-based version of the same project.

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
- MySQL running locally if you want to use the provided datasource config (update application.yml accordingly)

Notes
- Kronos versions and Kotlin versions are aligned with the original pom.xml (Kotlin 2.2.0; Kronos 0.0.6-SNAPSHOT).
- Sonatype Snapshots repository is enabled because Kronos uses snapshot artifacts.
- The Maven variant uses the kronos-maven-plugin for compile-time features. If you need the same compile-time features on Gradle, please configure the Kronos compiler plugin for Gradle (this sample includes runtime libraries and standard Spring Boot/Kotlin plugins).

For more about Kronos, see https://www.kotlinorm.com/
