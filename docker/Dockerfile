FROM openjdk:8-jre
MAINTAINER hien nguyen hienminhnguyen711@gmail.com
ADD  ${artifactId}-${version}.jar app.jar
EXPOSE 8082
#EXPOSE 8080 8081
ENTRYPOINT ["java", "-Dserver.port=8080", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]




#WORKDIR /app
#VOLUME ["/app"]
#COPY maven/app.jar app.jar
#COPY maven/start.sh start.sh
#COPY maven/wait-for-it.sh wait-for-it.sh
#RUN sh -c 'touch app.jar'
#ENTRYPOINT ["./start.sh"]







#COPY shopping-service-dw.jar /opt/
#COPY config.yml /opt/
#
#CMD java -jar \
#    /opt/shopping-service-dw.jar server \
#    /opt/config.yml
