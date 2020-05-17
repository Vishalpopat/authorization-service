package com.uxpsystems.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * This method configures the Swagger API specification and UI.
	 *
	 * @return a Docket for Swagger.
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.uxpsystems.assignment.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
	            .title("Authorization Service")
	            .description("Authorization Service")
	            .termsOfServiceUrl("TOS")
	            .license("License")
	            .licenseUrl("License URL")
	            .version("1.0.0")
	            .build();
		
	}



}

