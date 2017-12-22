FROM openjdk:9-slim
VOLUME /tmp
ADD demo-IMAGE_VERSION.jar app.jar
EXPOSE 8080
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.data.mongodb.uri=mongodb://mapmongo:27017/mapdb  -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
