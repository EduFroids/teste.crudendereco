package com.teste.stoom.api.teste;

import java.io.IOException;

import com.teste.stoom.api.domain.model.GeocodingLocation;
import com.teste.stoom.api.domain.service.CadastroGeocodingService;

public class TesteGeocoding {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		CadastroGeocodingService cadastroGeocodingService = new CadastroGeocodingService();;

        GeocodingLocation location = cadastroGeocodingService.buscarLocaliacao("Avenida Dois", "1125", "Jardim São Marcos", "Valinhos", "SP");
        
        System.out.println("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude() + ".");

    }

}