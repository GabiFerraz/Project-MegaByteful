package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateAppointment {
    private final AppointmentGateway gateway;

    public Appointment execute(final int id, final Appointment appointment) {
        final var appointmentFound =
                gateway.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found for ID: " + id));

        appointmentFound.setId(appointment.getId());
        appointmentFound.setScheduleId(appointment.getScheduleId());
        appointmentFound.setServiceProviderID(appointment.getServiceProviderID());
        appointmentFound.setCustomerId(appointment.getCustomerId());

        return gateway.updateAppointment(appointmentFound);
    }
}
