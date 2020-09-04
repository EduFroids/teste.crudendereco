package com.teste.stoom.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.teste.stoom.api.property.ApiProperty;

@Configuration
public class ApiConfiguration {
	
	@Bean
	public ApiProperty apiProperty() {
		return new ApiProperty();
	}

}
