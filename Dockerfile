FROM openjdk:11-jre-slim
RUN mkdir /app
COPY ./target/forum-0.0.1-SNAPSHOT.jar /app/alura-forum.jar
WORKDIR /app
ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=${PORT}", "-jar", "alura-forum.jar"]
