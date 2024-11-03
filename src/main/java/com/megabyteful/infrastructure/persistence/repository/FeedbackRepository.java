package com.megabyteful.infrastructure.persistence.repository;

import com.megabyteful.infrastructure.persistence.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {

    List<FeedbackEntity> findByProfessionalIdAndServiceId(int professionalId, int serviceId);
}
