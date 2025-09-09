# kronos-example-spring-boot (Maven)

English | [简体中文](./README-zh_CN.md)

This is the Maven version of the project based on Spring Boot + Kronos ORM + Kotlin.

How to build and run (Maven):

- Build: mvn -q -DskipTests package
- Run (API only, no server-side templates): mvn spring-boot:run

Frontend (Vue) is in ../vue-project. Start it with:
- cd ../vue-project && npm install && npm run dev
- Then open http://localhost:5173

Notes:
- Requires JDK 17+
- Uses Kotlin ${kotlin.version} and Kronos ${kronos.version} as defined in pom.xml
- Sonatype snapshots repository is enabled for Kronos snapshot dependencies/plugins
