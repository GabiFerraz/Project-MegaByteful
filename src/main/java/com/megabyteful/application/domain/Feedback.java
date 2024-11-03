package com.megabyteful.application.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Feedback {
    private int id;

    @NotNull(message = "Rating is required")
    private Double rating;

    @Size(max = 500, message = "Comment must be less than 500 characters")
    private String comment;

    @NotNull(message = "Professional ID is required")
    private int professionalId;

    @NotNull(message = "Service ID is required")
    private int serviceId;

    public static Feedback createFeedback(
            final int id, Double rating, String comment, int professionalId, int serviceId) {

        return new Feedback(id, rating, comment, professionalId, serviceId);
    }
}
