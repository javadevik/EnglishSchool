FROM openjdk:11 AS BUILD_IMAGE
# download dependencies
RUN ./gradlew build -x :bootRepackage -x test --continue
COPY . .
RUN ./gradlew clean build

FROM openjdk:11
ADD --from=BUILD_IMAGE build/libs/app.jar .
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
