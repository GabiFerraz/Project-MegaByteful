package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.usecase.GetAppointment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/megabyteful/v1/appointments")
public class AppointmentController {

    private final GetAppointment getAppointment;

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(final @PathVariable int id) {

        return ResponseEntity.ok(getAppointment.execute(id));
    }

    @PostMapping("/createAppointment")
    public ResponseEntity<Appointment> createAppointment(final @RequestBody @Valid Appointment appointment) {

        return ResponseEntity.ok(appointment);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            final @PathVariable int id, final @RequestBody @Valid Appointment appointment) {

        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(final @PathVariable int id) {

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/notificationAppointment")
    public ResponseEntity<String> notificaCliente() {

        System.out.println("notificação enviada");

        return ResponseEntity.ok("Notificação enviada com sucesso");
    }
}
