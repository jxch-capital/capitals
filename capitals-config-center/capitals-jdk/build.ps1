docker buildx build --platform=linux/arm64,linux/amd64 -t jxch/capitals-jdk:21-$(Get-Date -Format 'yyyyMMddHH') -t jxch/capitals-jdk:21 -t jxch/capitals-jdk:latest . --push
