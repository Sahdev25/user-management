FROM eclipse-temurin:21

COPY target/*.jar user-management.jar
ENTRYPOINT ["java","-jar","/user-management.jar"]