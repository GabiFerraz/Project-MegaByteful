package com.megabyteful.infrastructure.persistence.repository;

import com.megabyteful.infrastructure.persistence.entity.AppointmentEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {

  Optional<AppointmentEntity> findById(final int id);

  void deleteById(final int id);

  boolean existsByScheduleIdAndCustomerIdAndServiceTime(
      final int scheduleId, final int customerId, final LocalDateTime serviceTime);
}
