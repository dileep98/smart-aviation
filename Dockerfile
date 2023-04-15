
#
# Build stage
#
FROM maven:3.8.6-openjdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM ibm-semeru-runtimes:open-11-jre-focal
COPY --from=build /home/app/target/smart-aviation-*.jar smart-aviation.jar
EXPOSE 9000
#ENV _JAVA_OPTIONS="-XX:MaxRAM=128m"
#CMD java $_JAVA_OPTIONS -Dspring.datasource.url=$SPRING_DATASOURCE_URL -Dspring.datasource.username=$SPRING_DATASOURCE_USERNAME -Dspring.datasource.password=$SPRING_DATASOURCE_PASSWORD -jar smart-aviation.jar
CMD java -Dspring.datasource.url=$SPRING_DATASOURCE_URL -Dspring.datasource.username=$SPRING_DATASOURCE_USERNAME -Dspring.datasource.password=$SPRING_DATASOURCE_PASSWORD -jar smart-aviation.jar