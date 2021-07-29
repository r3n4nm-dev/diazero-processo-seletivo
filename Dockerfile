FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/diazero-0.0.1-SNAPSHOT.jar diazero.jar
ENTRYPOINT ["java","-jar","/diazero.jar"]