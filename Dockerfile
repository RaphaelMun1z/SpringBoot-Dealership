FROM maven:openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21.0.1-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8889
ENTRYPOINT ["java","-jar","demo.jar"]