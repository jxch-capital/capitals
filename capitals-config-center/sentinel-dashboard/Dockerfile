FROM openjdk:21-jdk-slim

WORKDIR /app
ADD sentinel-dashboard.jar app.jar

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezone

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=8080 -jar app.jar"]
