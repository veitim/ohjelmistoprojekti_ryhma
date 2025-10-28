FROM eclipse-temurin:17-jdk-focal AS builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean package -DskipTests
RUN find ./target -type f -name '*.jar' -exec cp {} /opt/app/app.jar \; -quit
 
FROM registry.access.redhat.com/ubi8/openjdk-17-runtime:latest
COPY --from=builder /opt/app/app.jar /deployments/app.jar
EXPOSE 8080