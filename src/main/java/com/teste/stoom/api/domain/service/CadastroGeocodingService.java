package com.teste.stoom.api.domain.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.stoom.api.domain.model.GeocodingLocation;
import com.teste.stoom.api.domain.model.GeocodingResults;

@Service
public class CadastroGeocodingService {
	
	private static final String GEOCODING_RESOURCE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	private static final String API_KEY = "";
	
	public GeocodingLocation buscarLocaliacao(String rua, String numero, String bairro, String cidade, String estado) throws IOException, InterruptedException {
		
		HttpClient httpClient = HttpClient.newHttpClient();
		
		StringBuilder endereco = new StringBuilder();
		endereco.append(configurarString(rua)).append(",")
			.append(configurarString(numero)).append(",")
			.append(configurarString(bairro)).append(",")
			.append(configurarString(cidade)).append(",")
			.append(configurarString(estado));
		
		String requestUri = GEOCODING_RESOURCE + endereco.toString() + "&key=" + API_KEY;
		
		HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(3000)).build();
		
		HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());
		
		GeocodingLocation location = criarLocalizacao(httpResponse.body());
		
		return location;
	}

	private String configurarString(String str) {
		str = str.trim();
		
		if(str.contains(" "))
			str = str.replace(" ", "+");
		
		return str;
	}

	private GeocodingLocation criarLocalizacao(String response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		GeocodingResults results = mapper.readValue(response, GeocodingResults.class);
		
		return results.getResults().get(0).getGeometry().getLocation();
	}

}
