package com.megabyteful.infrastructure.persistence.repository;

import com.megabyteful.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    Optional<CustomerEntity> findByCpf(String cpf);

    void deleteByCpf(String cpf);
}
