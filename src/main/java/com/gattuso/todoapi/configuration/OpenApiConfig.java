package com.gattuso.todoapi.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("ToDo Api")
                        .description("This API allows users to manage various tasks, enabling them creating,reading,updating and deleting them.")
                        .version("1.0.0")
                        .contact(new Contact().url("https://github.com/Gattuso42")));
    }

}
