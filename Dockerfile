FROM php:alpine3.11

RUN apk --no-cache add composer bash openjdk8-jre

COPY composer.json .
COPY composer.lock .

RUN composer install

RUN apk del composer
