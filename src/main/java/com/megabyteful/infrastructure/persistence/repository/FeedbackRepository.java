package com.megabyteful.infrastructure.persistence.repository;

import com.megabyteful.infrastructure.persistence.entity.FeedbackEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {

  List<FeedbackEntity> findByProfessionalIdAndServiceId(int professionalId, int serviceId);
}
