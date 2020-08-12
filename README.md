# Simple Spring boot App
Code Coverage : 85%

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app to do crud operations.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.upwork.tag.config.TagApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Testing

Test case can be found at `com.upwork.tag.TagApplicationTests` under `test package`
Test coverage is 85%
Test can be run using IDE or by mvn command
```shell
mvn test
```

## Architecture

Application is build in layered architecture
 - Controller: Controller gets request from UI
 - Service: controller delegates request to service
 - Repository: service runs business logic and calls repository to perform CRUD operations.
 - Model :  Object created for request and response
 - Entity: Create to interact with repository. 
 - Config: Holds Java config file

# Assumptions
    - Only CRUD operation were requested.
    - No validation is done.
    - No custom exception handling is done and @ControllerAdvice is not implemented.
    - Swagger is not used hence POSTMAN or CURL can be used to access API.
