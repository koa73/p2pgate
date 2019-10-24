package com.p2pgate.lib.client.config;


import feign.Feign;
import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 *
 * Created by OAKutsenko on 05.10.2016.
 */


public class FeignSimpleConfig {
    public static final int FIVE_SECONDS = 25000;

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(FIVE_SECONDS, FIVE_SECONDS);
    }

    @Bean // Балансировщик отключается (Hystrix)
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}