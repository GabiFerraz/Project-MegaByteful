package com.megabyteful.application.gateway;

import com.megabyteful.application.domain.Feedback;
import java.util.List;
import java.util.Optional;

public interface FeedbackGateway {

    Feedback save(final Feedback feedback);

    Optional<Feedback> findById(final int id);

    List<Feedback> findByProfessionalAndService(final int professionalId, final int serviceId);

    Feedback update(final Feedback feedback);

    void delete(final int id);
}
