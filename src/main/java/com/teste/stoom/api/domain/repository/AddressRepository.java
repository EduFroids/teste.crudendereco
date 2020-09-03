package com.teste.stoom.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.stoom.api.domain.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
