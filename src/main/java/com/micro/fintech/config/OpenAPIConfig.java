package com.micro.fintech.config;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/31/2024 4:10 PM
@Last Modified 5/31/2024 4:10 PM
Version 1.0
*/

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {


    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public OpenAPI springDoc() {
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setName("bearerJWT");
        securityScheme.setBearerFormat("JWT");
        securityScheme.setScheme("bearer");
        securityScheme.setType(SecurityScheme.Type.HTTP);
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .version("v1")
                        .description("Task Management API"))
                .schemaRequirement("bearerJWT", securityScheme);
    }
}
