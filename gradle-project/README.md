# kronos-example-spring-boot (Gradle)

English | [简体中文](./README-zh_CN.md)

This is the Gradle version of the project based on Spring Boot + Kronos ORM + Kotlin.

How to build and run (Gradle):

- Using local Gradle: gradle bootRun
- Using Gradle wrapper (if added): ./gradlew bootRun

Frontend (Vue) is in `../vue-project`. Start it with:
- cd ../vue-project && npm install && npm run dev
- Then open http://localhost:5173

Notes:
- Requires JDK 17+
- Kotlin, Spring Boot and Kronos versions are defined in `gradle.properties` and `libs.versions.toml`
- This sample uses Kronos 0.2.3 and the `com.kotlinorm.kronos-gradle-plugin` compiler plugin
