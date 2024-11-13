# kronos-example-spring-boot

-------------------------

English | [简体中文](https://github.com/Kronos-orm/kronos-example-spring-boot/blob/main/README-zh_CN.md)

This is a sample project based on Springboot + Kronos ORM + JDK 17 + Maven + Kotlin 2.0.0.

If you would like to learn more about Kronos, please visit [Kronos](https://www.kotlinorm.com/).

## Introducing Maven dependencies

**1. Add Kronos dependency**

```xml

<dependencies>
    <dependency>
        <groupId>com.kotlinorm</groupId>
        <artifactId>kronos-core</artifactId>
        <version>${kronos.version}</version>
    </dependency>
</dependencies>
```

**2. Add Kotlin compiler plugin**

```xml

<plugins>
    <plugin>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-maven-plugin</artifactId>
        <extensions>true</extensions>
        <configuration>
            <compilerPlugins>
                <plugin>spring</plugin>
                <plugin>kronos-maven-plugin</plugin>
            </compilerPlugins>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-maven-allopen</artifactId>
                <version>${kotlin.version}</version>
            </dependency>
            <dependency>
                <groupId>com.kotlinorm</groupId>
                <artifactId>kronos-maven-plugin</artifactId>
                <version>${kronos.version}</version>
            </dependency>
        </dependencies>
    </plugin>
</plugins>
```

## Configure the data source

Objects can be created dynamically or through configuration files.

A custom wrapper was created in the
project: [SpringDataWrapper](https://github.com/Kronos-orm/kronos-example-spring-boot/blob/main/src/main/kotlin/com/kotlinorm/kronosSpringDemo/common/SpringDataWrapper.kt)
, you can modify or extend it, or use other data source wrappers.

### Dynamic object creation

```kotlin
import com.kotlinorm.kronosSpringDemo.common.SpringDataWrapper.Companion.wrap

val dataSource = HikariDataSource().apply {
    driverClassName = "com.mysql.cj.jdbc.Driver"
    jdbcUrl = "jdbc:mysql://localhost:3306/example"
    username = "root"
    password = "xxxx"
}.wrap()

kronos.dataSource = { dataSource }
```

### Creating objects via configuration files (springboot)

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/example
    username: root
    password: xxxx
    dbcp2:
      initial-size: 5
      max-total: 10
      max-idle: 5
      min-idle: 2
      max-wait-millis: 10000
```

```kotlin
import org.springframework.boot.autoconfigure.SpringBootApplication
import com.kotlinorm.kronosSpringDemo.common.SpringDataWrapper.Companion.wrap

@Configuration
class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource() = BasicDataSource()
}

@SpringBootApplication
open class Application(val config: DataSourceConfig) {
    val dataSource by lazy { config.dataSource().wrap() }

    init {
        kronos.dataSource = { dataSource }
    }
}
```

## Run the project

After running the project, visit the following URL to view the results:

```
http://localhost:8080
```

If the interface returns the results shown below, Kronos has run successfully and the compiler plugin is working
properly.

![screen](https://github.com/Kronos-orm/kronos-example-spring-boot/blob/main/screenshot/img.png?raw=true)
