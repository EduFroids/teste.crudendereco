package com.teste.stoom.api.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("teste")
@Data
public class ApiProperty {
	
	private final GeocodingProperty geocodingProperty = new GeocodingProperty();
	
	@Data
	public static class GeocodingProperty {
		
		private String httpUrl;
		private String apiKey;
		private String paramAddress;
		private String paramKey;
		
	}

}
