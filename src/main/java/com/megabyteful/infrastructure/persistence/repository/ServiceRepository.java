package com.megabyteful.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megabyteful.infrastructure.persistence.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

	 Optional<ServiceEntity> findByNameAndIdProviderService(String name, Integer idProviderService);

	 Void deleteByNameAndIdProviderService(String name, Integer idProviderService);
	 
}