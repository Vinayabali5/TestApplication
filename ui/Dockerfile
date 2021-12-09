FROM node:16.3-alpine AS build

ARG BUILD_VERSION

WORKDIR /usr/src/app

COPY package.json package-lock.json ./
RUN npm install

COPY . .
ENV VERSION=${BUILD_VERSION}
RUN npm run build --prod


FROM nginx:1.17.1-alpine

WORKDIR /usr/share/nginx/html

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/ui /usr/share/nginx/html

CMD ["/bin/sh",  "-c", "envsubst < assets/env.template.js > assets/env.js && exec nginx -g 'daemon off;'"]
