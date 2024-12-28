#!/usr/bin/env sh
sudo buildx create --use
sudo docker buildx build --platform=linux/arm64,linux/amd64 -t jxch/capitals-web:$(date +%Y%m%d%H) -t jxch/capitals-web:latest . --push
