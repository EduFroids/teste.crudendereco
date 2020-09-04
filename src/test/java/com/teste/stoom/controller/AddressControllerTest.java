package com.teste.stoom.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.teste.stoom.api.controller.AddressController;
import com.teste.stoom.api.domain.model.Address;
import com.teste.stoom.api.domain.repository.AddressRepository;
import com.teste.stoom.api.domain.service.CadastroAddressService;

import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class AddressControllerTest {
	
	@Autowired
	private AddressController addressController;
	
	@MockBean
	private AddressRepository addressRepository;
	
	@MockBean
	private CadastroAddressService cadastroAddressService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.addressController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarTodosEnderecos() {
		
		Address address = new Address();
		address.setId(1L);
		address.setStreetName("Rua 1");
		address.setNumber("1");
		address.setComplement("");
		address.setNeighbourhood("Bairro 1");
		address.setCity("Cidade 1");
		address.setState("SP");
		address.setCountry("Brasil");
		address.setZipcode("13270-000");
		address.setLatitude("");
		address.setLongitude("");
		
		List<Address> list = new ArrayList<Address>() {
			{
				add(address);
			}
		};
		
		when(this.addressRepository.findAll()).thenReturn(list);
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/address")
		.then()
		.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarEndereco() {
		Address address = new Address();
		address.setId(1L);
		address.setStreetName("Rua 1");
		address.setNumber("1");
		address.setComplement("");
		address.setNeighbourhood("Bairro 1");
		address.setCity("Cidade 1");
		address.setState("SP");
		address.setCountry("Brasil");
		address.setZipcode("13270-000");
		address.setLatitude("");
		address.setLongitude("");
		
		when(this.cadastroAddressService.buscar(1L)).thenReturn(address);
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/address/{id}", 1L)
		.then()
		.statusCode(HttpStatus.OK.value());
	}
	
//	@Test
//	public void deveRetornarSucesso_QuandoAdicionarEndereco() {
//		Address address = new Address();
//		address.setId(1L);
//		address.setStreetName("Rua 1");
//		address.setNumber("1");
//		address.setComplement("");
//		address.setNeighbourhood("Bairro 1");
//		address.setCity("Cidade 1");
//		address.setState("SP");
//		address.setCountry("Brasil");
//		address.setZipcode("13270-000");
//		address.setLatitude("");
//		address.setLongitude("");
//		
//		when(this.cadastroAddressService.salvar(address)).thenReturn(address);
//		
//		given()
//		.accept(ContentType.JSON)
//		.when()
//		.post("/address", address)
//		.then()
//		.statusCode(HttpStatus.CREATED.value());
//		
//	}
	
	@Test
	public void deveRetornarSucesso_QuandoRemoverEndereco() {
		
		given()
		.accept(ContentType.JSON)
		.when()
		.delete("/address/{id}", 1L)
		.then()
		.statusCode(HttpStatus.NO_CONTENT.value());
	}

}
