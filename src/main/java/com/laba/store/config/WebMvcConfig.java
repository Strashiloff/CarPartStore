package com.laba.store.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class WebMvcConfig {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return container ->{
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
            container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/"));
        };
    }
}
