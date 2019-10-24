FROM redis:5.0.6-alpine

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

# add a simple script that can auto-detect the appropriate JAVA_HOME value
# based on whether the JDK or only the JRE is installed
RUN { \
	echo '#!/bin/sh'; \
	echo 'set -e'; \
	echo; \
	echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
    } > /usr/local/bin/docker-java-home \
    && chmod +x /usr/local/bin/docker-java-home
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk/jre
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin


ENV JAVA_VERSION 8u222
ENV JAVA_ALPINE_VERSION 8.222.10-r0


RUN apk add --update \
curl \
openjdk8-jre-base="$JAVA_ALPINE_VERSION" \
&& rm /var/cache/apk/*  && echo securerandom.source=file:/dev/urandom >> /usr/lib/jvm/default-jvm/jre/lib/security/java.security \
&& [ "$JAVA_HOME" = "$(docker-java-home)" ]


RUN mkdir /usr/local/p2p-gate \
    && mkdir /usr/local/p2p-gate/bin \
    && mkdir /usr/local/p2p-gate/etc \
    && mkdir /usr/local/p2p-gate/log \
    && mkdir /usr/local/p2p-gate/run

COPY bank-gate.sh /usr/local/p2p-gate/
COPY config /usr/local/p2p-gate/etc/
COPY bank-gate.jar /usr/local/p2p-gate/bin/

CMD /usr/local/p2p-gate/bank-gate.sh start && redis-server

EXPOSE 8226



