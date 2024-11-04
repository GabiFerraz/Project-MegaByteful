package com.megabyteful.infrastructure.persistence.repository;

import com.megabyteful.infrastructure.persistence.entity.ScheduleEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {

  Optional<ScheduleEntity> findByServiceTime(final LocalDateTime serviceTime);
}
