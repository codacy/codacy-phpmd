FROM alpine:3.17.0 as base

RUN apk add --no-cache php bash php-simplexml php-dom php-tokenizer openjdk8-jre


FROM base as builder

WORKDIR /workdir
COPY composer.* ./
RUN apk --no-cache add php-phar php-iconv php-openssl php-xml composer && composer install --no-scripts


FROM base

WORKDIR /workdir
COPY docs /docs
COPY target/universal/stage/ .
COPY --from=builder /workdir/vendor vendor

RUN chmod +x bin/codacy-phpmd && adduser --uid 2004 --disabled-password --gecos "" docker
USER docker
ENTRYPOINT ["bin/codacy-phpmd"]
