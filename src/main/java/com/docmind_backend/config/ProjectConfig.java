package com.docmind_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration 
public class ProjectConfig {

    @Bean 
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("DocMind - AI Documentation Inteli")
                .description("API documentation for DocMind backend")
                .version("1.0.0")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("DocMind Team")
                        .email("test@gmail.com")
                        .url("https://www.docmind.com")));
    }

}
