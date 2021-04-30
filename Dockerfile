FROM openjdk:8-jdk-alpine
EXPOSE 3000
VOLUME /tmp
WORKDIR /
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE}  oasis-translator.jar
CMD ["java","-jar","oasis-translator.jar"]