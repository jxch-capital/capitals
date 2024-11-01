docker buildx build --platform=linux/arm64,linux/amd64 -t jxch/capitals-chart-webapp:$(Get-Date -Format 'yyyyMMddHH') -t jxch/capitals-chart-webapp:latest . --push


