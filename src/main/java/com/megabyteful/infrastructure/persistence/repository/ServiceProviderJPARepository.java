package com.megabyteful.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megabyteful.infrastructure.persistence.entity.ServiceProviderJPAEntity;

public interface ServiceProviderJPARepository extends JpaRepository<ServiceProviderJPAEntity, Long>{

}
