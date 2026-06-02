FROM docker.io/library/maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY . .
RUN mvn -B -ntp -DskipTests package \
    && cp telegram-bot/target/*.jar /tmp/app.jar

FROM docker.io/library/eclipse-temurin:17-ubi9-minimal
WORKDIR /app

COPY --from=build /tmp/app.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
