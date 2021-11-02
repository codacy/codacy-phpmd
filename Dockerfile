FROM alpine:3.14.2 as base

RUN apk add --no-cache php8 bash php8-simplexml php8-dom php8-tokenizer openjdk8-jre && \
    ln -s /usr/bin/php8 /usr/bin/php

FROM base as builder

WORKDIR /workdir

COPY composer.* ./

# HACK: Make iconv work on Alpine + PHP8
# https://github.com/docker-library/php/issues/240#issuecomment-762763705
ENV LD_PRELOAD /usr/lib/preloadable_libiconv.so
RUN apk add --no-cache --repository http://dl-cdn.alpinelinux.org/alpine/v3.13/community/ gnu-libiconv=1.15-r3 && \
    apk --no-cache add php8-phar php8-iconv php8-openssl php8-xml && \
    wget -O /usr/bin/composer https://getcomposer.org/download/2.1.9/composer.phar && \
    chmod +x /usr/bin/composer && \
    composer install --no-scripts

FROM base

WORKDIR /workdir

COPY docs /docs
COPY target/universal/stage/ .
COPY --from=builder /workdir/vendor vendor
RUN chmod +x bin/codacy-phpmd && \
    adduser --uid 2004 --disabled-password --gecos "" docker
USER docker
ENTRYPOINT ["bin/codacy-phpmd"]
