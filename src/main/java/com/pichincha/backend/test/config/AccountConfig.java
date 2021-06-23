package com.pichincha.backend.test.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AccountConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Accounts API")
						.version("1.0")
						.description("Documentacion de servicios Web")
						.termsOfService("https://www.grupopichincha.com.ec/terms/")
						.license(new License().name("Apache 2.0").url("https://www.grupopichincha.com.ec/accounts/api/v1/licence/")));
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
				= new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
