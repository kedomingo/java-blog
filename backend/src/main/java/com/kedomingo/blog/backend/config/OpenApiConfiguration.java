package com.kedomingo.blog.backend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfiguration {
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Blog API")
            .description("Blog REST API.")
            .version("v1"))
        .externalDocs(new ExternalDocumentation()
            .description("Blog Repo")
            .url("https://github.com/kedomingo/java-blog"));
  }
}
