FROM azul/zulu-openjdk:17
EXPOSE 8081
ARG JAR_FILE=build/libs/dango-0.0.1-SNAPSHOT
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]