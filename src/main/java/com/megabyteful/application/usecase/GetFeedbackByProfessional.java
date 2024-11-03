package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Feedback;
import com.megabyteful.application.gateway.FeedbackGateway;
import com.megabyteful.application.usecase.exception.FeedbackNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetFeedbackByProfessional {

    private final FeedbackGateway gateway;

    public List<Feedback> execute(final int professionalId, final int serviceId) {
        List<Feedback> feedbacks = gateway.findByProfessionalAndService(professionalId, serviceId);


        if (feedbacks.isEmpty()) {
            throw new FeedbackNotFoundException(professionalId);
        }

        return feedbacks;
    }
}
