package com.teste.stoom.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingLocation {
	
	@JsonProperty("lat")
	private String latitude;
	
	@JsonProperty("lng")
	private String longitude;

}