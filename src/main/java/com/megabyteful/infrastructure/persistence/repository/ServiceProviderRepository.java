package com.megabyteful.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megabyteful.infrastructure.persistence.entity.ServiceProviderEntity;

public interface ServiceProviderRepository extends JpaRepository<ServiceProviderEntity, Integer>{

	Optional<ServiceProviderEntity> findByDocument(String document);
	
	void deleteByDocument(String document);
}
