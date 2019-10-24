# !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
#  Docker for VK Wallet refill gate
# !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
FROM selenium/standalone-chrome

USER root

RUN mkdir /usr/local/p2p-gate \
    && mkdir /usr/local/p2p-gate/bin \
    && mkdir /usr/local/p2p-gate/etc \
    && mkdir /usr/local/p2p-gate/log \
    && mkdir /usr/local/p2p-gate/run \
    && mkdir /usr/local/p2p-gate/drivers \
    && mkdir /usr/local/p2p-gate/drivers/linux

COPY vk-gate.sh /usr/local/p2p-gate/
COPY config /usr/local/p2p-gate/etc/
COPY chromedriver /usr/local/p2p-gate/drivers/linux/
COPY vk-gate.jar /usr/local/p2p-gate/bin/

RUN chmod +x /usr/local/p2p-gate/vk-gate.sh
RUN chmod +x /usr/local/p2p-gate/drivers/linux/chromedriver
RUN chown -R  seluser: /usr/local/p2p-gate

USER seluser


ENTRYPOINT /usr/local/p2p-gate/vk-gate.sh start && tail -f /usr/local/p2p-gate/log/vk-gate.log

EXPOSE 8336
