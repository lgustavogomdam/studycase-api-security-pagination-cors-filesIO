package com.netlify.lggdev.crudsistemamedico.infra.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

     @Value("${cors.origin-alloweds-pattern:default}")
     private String corsOriginAllowedsPatterns = "";

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**") // Padrão de URL para os quais as políticas se aplicarão
                .allowedOrigins(corsOriginAllowedsPatterns)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("Authentication") // Cabeçalhos permitidos
                .allowCredentials(true) // Permissão para enviar cookies e authentication credentials
                .maxAge(3600); // Tempo máximo que a resposta ao preflight request pode ser cacheada pelo navegador
    }
}
