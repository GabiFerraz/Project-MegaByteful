package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Feedback;
import com.megabyteful.application.gateway.FeedbackGateway;
import com.megabyteful.application.usecase.exception.FeedbackAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateFeedback {

    private final FeedbackGateway gateway;

    public Feedback execute(final Feedback feedbackDomain) {

        final var existingFeedbacks = gateway.findByProfessionalAndService(feedbackDomain.getProfessionalId(), feedbackDomain.getServiceId());

        if (!existingFeedbacks.isEmpty()) {
            throw new FeedbackAlreadyExistsException(feedbackDomain.getProfessionalId(), feedbackDomain.getServiceId());
        }

        final var buildDomain = Feedback.createFeedback(
                feedbackDomain.getId(),
                feedbackDomain.getRating(),
                feedbackDomain.getComment(),
                feedbackDomain.getProfessionalId(),
                feedbackDomain.getServiceId()
        );

        return gateway.save(buildDomain);
    }
}
