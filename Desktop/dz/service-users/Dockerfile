FROM openjdk:17-alpine

WORKDIR /opt/server
COPY ./out/artifacts/service_users_jar/service-users.jar server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "server.jar"]
