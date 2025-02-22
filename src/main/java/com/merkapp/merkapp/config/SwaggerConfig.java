package com.merkapp.merkapp.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

//Usamos Swagger para la documnetación de nuestra API
@Configuration
public class SwaggerConfig {

    @Bean //Usamos esta anotación para que nosotros tengamos el control de como creamos el objeto pero que  Spring Boot lo administre.
    //Entonces en este caso lo usamos hacer la configuarcion de Swagger cuando springboot se inicialice.
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("public-apis").pathsToMatch("/**").build(); // Creamos un grupo public-apis que incluye todas las rutas en la documentación
    }

    @Bean
    OpenAPI customOpenAPI() {
        //Aqui hacmeos la configuración general de la documentación Swagger
        return new OpenAPI().info(new Info().title("Merkapp API").version("1.0"))
                //Aca especificamos que la documentación necesita autenticación con token  JWT
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth")).components(
                        new Components().addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

}
