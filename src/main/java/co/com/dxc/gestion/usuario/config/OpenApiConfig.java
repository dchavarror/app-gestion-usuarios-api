package co.com.dxc.gestion.usuario.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

	private final Environment env;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title(env.getProperty("spring.application.name") + " Service API")
				.version("1.0").description(env.getProperty("spring.application.name") + " API Description")
				.termsOfService("https://dcx.com.co")
				.contact(new Contact().name("DCX").email("admin@dcx.com.co"))
				.license(new License().name("LICENSE").url("LICENSE URL")));
	}

	@Bean
	public GroupedOpenApi groupPermitted() {
		return GroupedOpenApi.builder().group(env.getProperty("spring.application.name"))
				.packagesToScan("co.com.dxc.gestion.usuario.controllers")
				.build();
	}
}