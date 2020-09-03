package com.teste.stoom.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.teste.stoom.api.domain.exception.AddressNaoEncontradoException;
import com.teste.stoom.api.domain.model.Address;
import com.teste.stoom.api.domain.repository.AddressRepository;

@Service
public class CadastroAddressService {

	@Autowired
	private AddressRepository addressRepository;

	public Address salvar(Address address) {
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