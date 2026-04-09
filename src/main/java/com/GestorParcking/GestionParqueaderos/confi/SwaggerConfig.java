package com.GestorParcking.GestionParqueaderos.confi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Parqueaderos")
                        .version("1.0")
                        .description("Sistema para el control de entradas, salidas y mensualidades de vehículos.")
                        .contact(new Contact()
                                .name("Brandom Alvarez Posada y Irma Sofia Moreno")
                                .email("brandomalvarez1128887@correo.itm.edu.co")));
    }
}