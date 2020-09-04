package com.teste.stoom.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Address {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String streetName;
	
	@NotBlank
	@Column(name = "address_number", nullable = false)
	private String number;
	
	private String complement;
	
	@NotBlank
	@Column(nullable = false)
	private String neighbourhood;
	
	@NotBlank
	@Column(nullable = false)
	private String city;
	
	@NotBlank
	@Size(min = 2, max = 2)
	@Column(nullable = false)
	private String state;
	
	@NotBlank
	@Column(nullable = false)
	private String country;
	
	@NotBlank
	@Size(min = 9, max = 9)
	@Column(nullable = false)
	private String zipcode;
	
	private String latitude;
	
	private String longitude;
	
}