FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ls -R /app/src
RUN mvn clean package
RUN ls -l /app/target

FROM bitnami/spark:3.4.1  
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar
CMD ["/opt/bitnami/spark/bin/spark-submit", "--class", "com.example.StreamingApp", "--master", "spark://spark-master:7077", "/app/app.jar"]