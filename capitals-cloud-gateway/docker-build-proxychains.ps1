proxychains docker buildx build --platform=linux/arm64,linux/amd64 -t jxch/capitals-cloud-gateway:$(Get-Date -Format 'yyyyMMddHH') -t jxch/capitals-cloud-gateway:latest . --push
