package com.p2pgate.lib.client.config;


import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 *
 * Created by OAKutsenko on 05.10.2016.
 */


public class FeignSimpleEncoderConfig {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    private static final int FIVE_SECONDS = 5000;

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(FIVE_SECONDS, FIVE_SECONDS);
    }

    @Bean
    public Encoder encoder(){
        return new FormEncoder();
    }


    @Bean // Балансировщик отключается (Hystrix)
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}