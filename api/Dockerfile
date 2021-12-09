FROM gradle:7.1.1-jdk11 AS build

ARG BUILD_VERSION
ENV VERSION=${BUILD_VERSION}

WORKDIR /build
COPY . /build/

RUN gradle -x test build

FROM openjdk:11

WORKDIR  /app
RUN mkdir /app/uploads

COPY ./env-file-var.sh .
RUN chmod 755 env-file-var.sh

COPY ./application-env.yml application.yml
COPY --from=build /build/build/libs/online-application-api.jar /app/app.jar

ENTRYPOINT ["./env-file-var.sh", "java",  "-jar",  "app.jar"]
