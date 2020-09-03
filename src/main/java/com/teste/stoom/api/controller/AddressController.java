package com.teste.stoom.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teste.stoom.api.domain.model.Address;
import com.teste.stoom.api.domain.repository.AddressRepository;
import com.teste.stoom.api.domain.service.CadastroAddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CadastroAddressService cadastroAddressService;
	
	@GetMapping
	public List<Address> listar() {
		return addressRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Address buscar(@PathVariable Long id) {
		return cadastroAddressService.buscar(id);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Address adicionar(@RequestBody @Valid Address address) {
		return cadastroAddressService.salvar(address);
	}
	
	@PutMapping("/{id}")
	public Address atualizar(@PathVariable Long id, @RequestBody Address address) {
		Address addressAtual = cadastroAddressService.buscar(id);
		
		BeanUtils.copyProperties(address, addressAtual, "id");
			
		return cadastroAddressService.salvar(addressAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroAddressService.excluir(id);
	}
}
