package com.luciano.item.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfig {
    
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Item API")
                        .version("v1")
                        .description("API to manage item")
                        .contact(new Contact()
                                        .name("l.bigiotti")
                                        .email("luciano.bigiotti@gmail.com"))
                );
    }
}
