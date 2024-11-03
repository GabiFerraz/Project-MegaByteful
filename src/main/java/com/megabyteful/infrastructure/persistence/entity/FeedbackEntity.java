package com.megabyteful.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double rating;

    @Column(length = 500)
    private String comment;

    @Column(name = "professional_id")
    private int professionalId;

    @Column(name = "service_id")
    private int serviceId;
}
