package com.megabyteful.infrastructure.persistence.repository;

import com.megabyteful.infrastructure.persistence.entity.ServiceProviderEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProviderRepository extends JpaRepository<ServiceProviderEntity, Integer> {

  Optional<ServiceProviderEntity> findByDocument(String document);

  void deleteByDocument(String document);
}
