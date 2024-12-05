FROM openjdk:21-jdk-slim

WORKDIR /app
ADD capitals-cloud-admin.jar app.jar

RUN apt-get update && apt-get install -y --no-install-recommends tzdata fontconfig && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezone && apt-get install -y --no-install-recommends fonts-dejavu && apt-get clean && rm -rf /var/lib/apt/lists/*

ENV PROFILES_ACTIVE=prod
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=${PROFILES_ACTIVE} -Dserver.port=8080 -jar app.jar"]
