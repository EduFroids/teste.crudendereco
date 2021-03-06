package com.teste.stoom.api.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingResults {
	
	List<GeocodingObject> results;
	
	@JsonProperty("status")
	private String status;

	public boolean isOk() {
		if(this.status.equalsIgnoreCase("OK"))
			return true;
		
		return false;
	}
	
}
