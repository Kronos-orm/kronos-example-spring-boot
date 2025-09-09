# kronos-example-spring-boot (Gradle)

English | [简体中文](./README-zh_CN.md)

This is the Gradle version of the project based on Spring Boot + Kronos ORM + Kotlin.

How to build and run (Gradle):

- Using local Gradle: gradle bootRun
- Using Gradle wrapper (if added): ./gradlew bootRun

This module now serves only REST APIs (no server-side pages).
Frontend (Vue) is in ../vue-project. Start it with:
- cd ../vue-project && npm install && npm run dev
- Then open http://localhost:5173

Notes:
- Requires JDK 17+
- Kotlin, Spring Boot and Kronos versions are defined in gradle.properties
- Sonatype snapshots repository is enabled for Kronos snapshot dependencies
- The Kronos compiler plugin is required in Maven and may also be needed for Gradle; this sample includes runtime libraries. If you need compile-time features, please configure the Kronos compiler plugin for Gradle accordingly.
