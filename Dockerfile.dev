FROM maven:3.5.4-jdk-8-alpine as maven
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN #mvn dependency:go-offline -B
RUN mvn clean install
FROM openjdk:8u171-jre-alpine
WORKDIR /projects
COPY --from=maven target/docker_test-*with-dependencies.jar ./APIController.jar
CMD ["jar xf APIController.jar "]
CMD ["java", "-jar", "./APIController.jar"]