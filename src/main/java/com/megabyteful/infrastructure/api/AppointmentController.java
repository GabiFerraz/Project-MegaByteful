package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Appointment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/megabyteful/v1/appointments")
public class AppointmentController {

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> findByAppointmentId(final @PathVariable String appointmentId) {

        Appointment appointment = new Appointment();
        appointment.setId(1);
        appointment.setCustomerId(1);
        appointment.setScheduleId(1);
        appointment.setServiceProviderID(1);

        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/createAppointment")
    public ResponseEntity<Appointment> createAppointment(final @RequestBody @Valid Appointment appointment) {



        return ResponseEntity.ok(appointment);
    }


    @PutMapping("/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment(
            final @PathVariable int appointmentId, final @RequestBody @Valid Appointment appointment) {

        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(final @PathVariable int appointmentId) {

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/notificationAppointment")
    public ResponseEntity<String> notificaCliente() {

        System.out.println("notificação enviada");

        return ResponseEntity.ok("Notificação enviada com sucesso");
    }
}
