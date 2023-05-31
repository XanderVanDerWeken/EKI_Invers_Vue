# Build Image
FROM maven:3.8.5-openjdk-11-slim as build
WORKDIR /workspace/app

# Copy POM
COPY pom.xml pom.xml

# MVN get Dependencies
RUN mvn dependency:go-offline

# Copy Source
COPY src src

# Build Jar
RUN mvn package -DskipTests

# Final Image
FROM openjdk:11.0.14.1-jre-slim
ARG DEPENDENCY=/workspace/app/target

# Copy build results
WORKDIR /app
COPY --from=build ${DEPENDENCY}/lib ./lib
COPY --from=build ${DEPENDENCY}/invers_server.jar .

# Expose Server Port
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "invers_server.jar"]
