package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.gateway.AppointmentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAppointment {

    private final AppointmentGateway gateway;

    public Appointment execute(final int id) {
        return gateway.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }
}
