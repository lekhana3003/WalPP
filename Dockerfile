# our base build image
FROM maven:3.6.0-jdk-11 as maven

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn clean package -DskipTests
RUN find / -type f -name "WalPP*"
# our final base image
FROM openjdk:11-jre-slim

# set deployment directory

WORKDIR /walpp

# copy over the built artifact from the maven image
COPY --from=maven target/WalPP-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080

# set the startup command to run your binary
CMD ["java", "-jar", "./WalPP-0.0.1-SNAPSHOT.jar"]