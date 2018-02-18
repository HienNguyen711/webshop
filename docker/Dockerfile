FROM openjdk:8-jre
ADD  ${artifactId}-${version}.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-Dserver.port=8080", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]




#WORKDIR /app
#VOLUME ["/app"]
#COPY maven/app.jar app.jar
#COPY maven/start.sh start.sh
#COPY maven/wait-for-it.sh wait-for-it.sh
#RUN sh -c 'touch app.jar'
#ENTRYPOINT ["./start.sh"]
