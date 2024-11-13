# kronos-example-spring-boot

-------------------------

[English](https://github.com/Kronos-orm/kronos-example-spring-boot/blob/main/README.md) | 简体中文

这是一个基于Springboot + Kronos ORM + JDK 17 + Maven + Kotlin 2.0.0的示例项目。

如果您想了解更多关于Kronos的信息，请访问[Kronos](https://www.kotlinorm.com/)。

## 引入Maven依赖

**1. 添加Kronos依赖**

```xml

<dependencies>
    <dependency>
        <groupId>com.kotlinorm</groupId>
        <artifactId>kronos-core</artifactId>
        <version>${kronos.version}</version>
    </dependency>
</dependencies>
```

**2. 添加Kotlin编译器插件**

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

## 配置数据源

可以动态创建对象，也可以通过配置文件创建对象。


项目中创建了一个自定义包装器：[SpringDataWrapper](https://github.com/Kronos-orm/kronos-example-spring-boot/blob/main/src/main/kotlin/com/kotlinorm/kronosSpringDemo/common/SpringDataWrapper.kt)
，您可以修改或扩展它，也可以使用其他数据源包装器。

### 动态创建对象

```kotlin
val dataSource = HikariDataSource().apply {
    driverClassName = "com.mysql.cj.jdbc.Driver"
    jdbcUrl = "jdbc:mysql://localhost:3306/example"
    username = "root"
    password = "xxxx"
}.wrap()

kronos.dataSource = { dataSource }
```

### 通过配置文件创建对象(springboot)

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
        kronos.dataSource = { dataSource.wrap }
    }
}
```

## 运行项目

运行项目后，访问以下URL，即可查看结果：

```
http://localhost:8080
```

如果接口返回的结果如下图所示，则表示Kronos已成功运行，编译器插件也已正常工作。

![screen](https://github.com/Kronos-orm/kronos-example-spring-boot/blob/main/screenshot/img.png?raw=true)
