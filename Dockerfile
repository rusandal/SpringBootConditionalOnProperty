FROM adoptopenjdk/openjdk11
EXPOSE 8081
ADD target/SpringParameters-0.0.1-SNAPSHOT.jar devapp.jar
CMD ["java","-jar","/devapp.jar"]