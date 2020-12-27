FROM redis:5.0.6-alpine

# Install OpenJDK-8
RUN apk --update add --no-cache openjdk8

# Setup JAVA_HOME -- useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk/
RUN export JAVA_HOME

RUN mkdir /usr/local/p2p-gate \
    && mkdir /usr/local/p2p-gate/bin \
    && mkdir /usr/local/p2p-gate/etc \
    && mkdir /usr/local/p2p-gate/log \
    && mkdir /usr/local/p2p-gate/run

COPY bank-gate.sh /usr/local/p2p-gate/
COPY config /usr/local/p2p-gate/etc/
COPY bank-gate.jar /usr/local/p2p-gate/bin/

RUN chmod +x /usr/local/p2p-gate/bank-gate.sh

CMD /usr/local/p2p-gate/bank-gate.sh start && redis-server

EXPOSE 8226


