FROM openjdk:11-jdk

EXPOSE 8080
ADD ./build/libs/*.jar kmong.jar

CMD ["java", "-jar", "-Dspring.profiles.active=local", "kmong.jar"]