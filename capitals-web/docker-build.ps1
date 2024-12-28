docker buildx build --platform=linux/arm64,linux/amd64 -t jxch/capitals-web:$(Get-Date -Format 'yyyyMMddHH') -t jxch/capitals-web:latest . --push
