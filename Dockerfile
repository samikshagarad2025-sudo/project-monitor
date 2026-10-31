FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY src ./src
COPY public ./public

RUN mkdir -p out && javac -d out src/*.java

ENV PORT=8080

EXPOSE 8080

CMD ["java", "-cp", "out", "WebServer"]
