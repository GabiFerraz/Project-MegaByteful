package com.megabyteful.infrastructure.api.fixture;

import com.megabyteful.application.domain.Appointment;

public class AppointmentControllerTestFixture {

    public static Appointment validAppointmentRequest() {
        return Appointment.builder()
                .id(999)
                .serviceProviderID(999)
                .scheduleId(999)
                .customerId(999)
                .build();
    }

    public static Appointment validAppointmentResponse() {
        return Appointment.builder()
                .id(999)
                .serviceProviderID(999)
                .scheduleId(999)
                .customerId(999)
                .build();
    }
}
