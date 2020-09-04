package com.teste.stoom.api.domain.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.stoom.api.domain.exception.GeocodingException;
import com.teste.stoom.api.domain.model.GeocodingErrors;
import com.teste.stoom.api.domain.model.GeocodingLocation;
import com.teste.stoom.api.domain.model.GeocodingResults;
import com.teste.stoom.api.property.ApiProperty;

@Service
public class CadastroGeocodingService {
	
	@Autowired
	private ApiProperty apiProperty;
	
	public GeocodingLocation buscarLocaliacao(String rua, String numero, String bairro, String cidade, String estado) throws IOException, InterruptedException {
		
		HttpClient httpClient = HttpClient.newHttpClient();
		GeocodingLocation location = null;
		
		StringBuilder endereco = new StringBuilder();
		endereco.append(configurarString(rua)).append(",")
			.append(configurarString(numero)).append(",")
			.append(configurarString(bairro)).append(",")
			.append(configurarString(cidade)).append(",")
			.append(configurarString(estado));
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
				.fromHttpUrl(apiProperty.getGeocodingProperty().getHttpUrl())
				.queryParam(apiProperty.getGeocodingProperty().getParamAddress(), endereco)
				.queryParam(apiProperty.getGeocodingProperty().getParamKey(), apiProperty.getGeocodingProperty().getApiKey());
		
		HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(uriComponentsBuilder.toUriString()))
                .timeout(Duration.ofMillis(2000)).build();
		
		HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());
		
		Object object = criarLocalizacao(httpResponse.body());
		
		if(object instanceof GeocodingLocation) {
			location = (GeocodingLocation) object;
			
		} else {
			GeocodingErrors errors = (GeocodingErrors) object;
			
			throw new GeocodingException(errors.getErrorMessge());
		}
		
		return location;
	}

	private String configurarString(String str) {
		str = str.trim();
		
		if(str.contains(" "))
			str = str.replace(" ", "+");
		
		return str;
	}

	private Object criarLocalizacao(String response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		GeocodingResults results = mapper.readValue(response, GeocodingResults.class);
		
		if(results.isOk()) {
			return results.getResults().get(0).getGeometry().getLocation();
			
		} else {
			GeocodingErrors errors = mapper.readValue(response, GeocodingErrors.class);
			return errors;
			
		}
	}

}
