package com.teste.stoom.api.domain.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.teste.stoom.api.domain.exception.AddressNaoEncontradoException;
import com.teste.stoom.api.domain.exception.GeocodingException;
import com.teste.stoom.api.domain.model.Address;
import com.teste.stoom.api.domain.model.GeocodingLocation;
import com.teste.stoom.api.domain.repository.AddressRepository;

@Service
public class CadastroAddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CadastroGeocodingService cadastroGeocodingService;

	public Address salvar(Address address) {
		if((address.getLatitude() == null || address.getLatitude().equalsIgnoreCase("")) 
				&& (address.getLongitude() == null || address.getLongitude().equalsIgnoreCase(""))) {
			
			try {
				GeocodingLocation location = cadastroGeocodingService.buscarLocaliacao(
						address.getStreetName(), address.getNumber(), address.getNeighbourhood(), address.getCity(), address.getState());
				
				address.setLatitude(location.getLatitude());
				address.setLongitude(location.getLongitude());
				
			} catch (GeocodingException e) {
				throw new GeocodingException(e.getMessage());
			} catch (IOException e) {
				throw new GeocodingException(e.getMessage());
			} catch (InterruptedException e) {
				throw new GeocodingException(e.getMessage());
			}
		}
		
		return addressRepository.save(address);
	}

	public void excluir(Long id) {
		try {
			addressRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new AddressNaoEncontradoException(id);
		}
	}
	
	public Address buscar(Long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new AddressNaoEncontradoException(id));
	}
}