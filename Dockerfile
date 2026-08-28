FROM eclipse-temurin:21-jre
WORKDIR /app
RUN addgroup --system app && \
    adduser --system --ingroup app app
COPY --chown=app:app target/*.jar app.jar
USER app
EXPOSE 25414
LABEL authors="moodz"

ENTRYPOINT ["java", "-Xms128m", "-Xmx768m", "-jar", "app.jar"]