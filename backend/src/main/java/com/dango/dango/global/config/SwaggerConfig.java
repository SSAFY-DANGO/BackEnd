package com.dango.dango.global.config;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
			.version("v1.0.0")
			.title("API")
			.description("");

		String jwt = "JWT";
		SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt); // 헤더에 토큰 포함
		Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
			.name(jwt)
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT")
		);

		Server deployServer = new Server();
		deployServer.setUrl("https://j10a702.p.ssafy.io");

		Server devServer = new Server();
		devServer.setUrl("http://localhost:8081");

		return new OpenAPI()
			.info(info)
			.addSecurityItem(securityRequirement)
			.components(components)
			.servers(List.of(new Server[]{devServer,deployServer}));
	}

}