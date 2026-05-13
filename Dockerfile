FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/BARBEARIAPRO-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Configurações JVM otimizadas para containers
ENV JAVA_OPTS="-XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+ParallelRefProcEnabled"

HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 \
    CMD java -jar /app/app.jar --info || exit 1

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
