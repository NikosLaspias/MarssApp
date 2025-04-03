# -------- Build stage --------
    FROM maven:3.8.5-openjdk-17 AS build
    WORKDIR /app
    
    # Αντιγραφή Maven config & dependencies πρώτα για caching
    COPY pom.xml .
    RUN mvn dependency:go-offline
    
    # Αντιγραφή υπόλοιπου project
    COPY src ./src
    
    # Build εφαρμογής χωρίς tests (πιο γρήγορο)
    RUN mvn clean package -DskipTests
    
    # -------- Runtime stage --------
    FROM openjdk:17-jdk-slim
    WORKDIR /app
    
    # Αντιγραφή jar από build stage
    COPY --from=build /app/target/*.jar app.jar
    
    # Εξωτερική πόρτα εφαρμογής
    EXPOSE 8080
    
    # Εκτέλεση
    ENTRYPOINT ["java", "-jar", "app.jar"]
    