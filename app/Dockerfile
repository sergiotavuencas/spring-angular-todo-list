FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY /target/todo-list-0.0.1-SNAPSHOT.jar /opt/app
ENTRYPOINT ["java", "-jar", "/opt/app/todo-list-0.0.1-SNAPSHOT.jar"]