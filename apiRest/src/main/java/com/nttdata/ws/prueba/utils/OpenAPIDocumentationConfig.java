package com.nttdata.ws.prueba.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition
public class OpenAPIDocumentationConfig {

	@Bean
	public OpenAPI openApi() {
		return new OpenAPI().info(new Info().title("PruebaTecnicaES").description("Microservicio")
				.termsOfService("https://ec.linkedin.com/in/angelica-mac%C3%ADas-s%C3%A1nchez-808464171").version("1.0.0")
				.license(new License().name("Apache 2.0").url("https://www.gnu.org/licenses/gpl-3.0.html"))
				.contact(new io.swagger.v3.oas.models.info.Contact().email("angelica.macias.sa91@gmail.com")));
	}

}
