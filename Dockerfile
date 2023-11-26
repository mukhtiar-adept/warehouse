#Build Stage
# initialize build and set base image for first stage
FROM maven:3.8.2-eclipse-temurin-17 as build_stage


WORKDIR /opt/warehouse
# copy just pom.xml
COPY pom.xml .
# go-offline using the pom.xml
RUN mvn dependency:go-offline
# copy project files
COPY ./src ./src
# compile the source code and package it in a jar file
RUN mvn clean package -DskipTests=true



#Run Stage
# set base image for second stage
FROM eclipse-temurin:17.0.3_7-jre-focal
# set deployment directory
WORKDIR /opt/warehouse
# copy over the built artifact from the maven image
COPY --from=build_stage /opt/warehouse/target/warehouse-1.0.jar /opt/warehouse/app.jar
# expose prot
EXPOSE 8080
#Run application
ENV JAVA_OPTS=" -server -Xms2048m -Xmx2048m -XX:+UseG1GC -Xlog:gc*"
#ENTRYPOINT ["java", "-jar", "/opt/warehouse/app.jar"]
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/warehouse/app.jar

