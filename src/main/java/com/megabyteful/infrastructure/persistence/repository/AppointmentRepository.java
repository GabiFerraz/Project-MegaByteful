package com.megabyteful.infrastructure.persistence.repository;

import com.megabyteful.infrastructure.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

    Optional<AppointmentEntity> findById(int id);

    void deleteById(int id);

}
