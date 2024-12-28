FROM nginx:alpine

WORKDIR /capitals

COPY ./dist /capitals
COPY ./default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
