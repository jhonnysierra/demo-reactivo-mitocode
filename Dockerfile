FROM openjdk:17-alpine
COPY "./build/libs/demo-reactivo-mitocode-0.0.1-SNAPSHOT.jar" "webflux-mitocode.jar"
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "webflux-mitocode.jar"]