package com.jiofack.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Blog API")
                        .version("1.0.0")
                        .description("API permettant aux utilisateurs de publier des articles et d'ajouter des commentaires")
                );

    }
}
