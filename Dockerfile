# Use an official OpenJDK runtime as a parent image
FROM maven:3.8.3-openjdk-17

ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME

COPY ./pom.xml $HOME
# go-offline using the pom.xml
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline
# now copy the rest of the code and run an offline build
COPY . /
RUN --mount=type=cache,target=/root/.m2 mvn -o install
# Set the working directory in the containe
# RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package
# RUN --mount=type=cache,target=/root/.m2 mvn -o install
# RUN ./mvnw package -DskipTests
# Copy the application JAR file
COPY target/*-SNAPSHOT.jar app.jar

# Expose the port that the application runs on
EXPOSE 8083

# Set environment variables (these should be provided via docker-compose)

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
