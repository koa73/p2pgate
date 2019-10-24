package com.p2pgate.vk_gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 *
 * Created by OAKutsenko on 22.09.2019.
 */


//@SpringBootApplication( exclude={org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class})
@SpringBootApplication()
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties
@Configuration
@ComponentScan({"com.p2pgate.vk_gate","com.p2pgate.lib"})
@EnableFeignClients(basePackages = {"com.p2pgate.vk_gate.client"})
public class VkApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkApplication.class, args);
    }


    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
