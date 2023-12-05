FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
COPY --from=build /app/target/DesafioJava-0.0.1-SNAPSHOT.jar /DesafioJava-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/DesafioJava-0.0.1-SNAPSHOT.jar"]
