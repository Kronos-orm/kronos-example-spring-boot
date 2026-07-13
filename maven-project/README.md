# kronos-example-spring-boot (Maven)

English | [简体中文](./README-zh_CN.md)

This is the Maven version of the project based on Spring Boot + Kronos ORM + Kotlin.

How to build and run (Maven):

- Build: mvn -q -DskipTests package
- Run (API only, no server-side templates): mvn spring-boot:run

Frontend (Vue) is in `../vue-project`. Start it with:
- cd ../vue-project && npm install && npm run dev
- Then open http://localhost:5173

Notes:
- Requires JDK 17+
- Uses Kotlin 2.4.0 and Kronos 0.2.3 as defined in `pom.xml`
- Uses `kronos-maven-plugin` for compile-time Kronos support
