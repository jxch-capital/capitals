docker buildx build --platform=linux/arm64,linux/amd64 -t jxch/capitals-stock4j:$(Get-Date -Format 'yyyyMMddHH') -t jxch/capitals-stock4j:latest . --push
