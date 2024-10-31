FROM openjdk:21-jdk-slim

WORKDIR /app
ADD chart-webapp.jar app.jar

RUN apt-get update && apt-get install -y --no-install-recommends tzdata fontconfig && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezone && apt-get install -y --no-install-recommends fonts-dejavu && apt-get clean && rm -rf /var/lib/apt/lists/*
RUN mkdir /env && echo "$(curl -s ifconfig.me)" > /env/ip

ENV PROFILES_ACTIVE=prod
ENV SERVER_PORT=9005
ENV SERVER_IP=127.0.0.1
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "export SERVER_IP=$(cat /env/ip) && java -Dspring.profiles.active=${PROFILES_ACTIVE} -Dspring.cloud.nacos.discovery.ip=${SERVER_IP} -Dspring.cloud.nacos.discovery.port=${SERVER_PORT} -Dserver.port=${SERVER_PORT} -jar app.jar"]
