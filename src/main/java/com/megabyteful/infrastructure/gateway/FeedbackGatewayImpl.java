package com.megabyteful.infrastructure.gateway;

import com.megabyteful.application.domain.Feedback;
import com.megabyteful.application.gateway.FeedbackGateway;
import com.megabyteful.infrastructure.persistence.entity.FeedbackEntity;
import com.megabyteful.infrastructure.persistence.repository.FeedbackRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class FeedbackGatewayImpl implements FeedbackGateway {

  private final FeedbackRepository feedbackRepository;

  @Override
  public Feedback save(final Feedback feedback) {
    final var entity =
        FeedbackEntity.builder()
            .id(feedback.getId())
            .rating(feedback.getRating())
            .comment(feedback.getComment())
            .professionalId(feedback.getProfessionalId())
            .serviceId(feedback.getServiceId())
            .build();

    final var saved = feedbackRepository.save(entity);

    return this.toResponse(saved);
  }

  @Override
  public Optional<Feedback> findById(final int id) {
    return feedbackRepository.findById(id).map(this::toResponse);
  }

  @Override
  public List<Feedback> findByProfessionalAndService(
      final int professionalId, final int serviceId) {
    return feedbackRepository.findByProfessionalIdAndServiceId(professionalId, serviceId).stream()
        .map(this::toResponse)
        .toList();
  }

  @Override
  public Feedback update(final Feedback feedback) {
    final var feedbackFound =
        feedbackRepository
            .findById(feedback.getId())
            .orElseThrow(() -> new RuntimeException("Feedback not found"));

    final var newEntity =
        FeedbackEntity.builder()
            .id(feedbackFound.getId())
            .rating(feedback.getRating())
            .comment(feedback.getComment())
            .professionalId(feedback.getProfessionalId())
            .serviceId(feedback.getServiceId())
            .build();

    final var updated = feedbackRepository.save(newEntity);

    return this.toResponse(updated);
  }

  @Transactional
  @Override
  public void delete(final int id) {
    feedbackRepository.deleteById(id);
  }

  private Feedback toResponse(final FeedbackEntity entity) {
    return new Feedback(
        entity.getId(),
        entity.getRating(),
        entity.getComment(),
        entity.getProfessionalId(),
        entity.getServiceId());
  }
}
