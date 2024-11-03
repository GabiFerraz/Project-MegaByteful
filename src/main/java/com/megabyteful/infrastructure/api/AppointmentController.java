package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.usecase.*;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/megabyteful/appointments")
public class AppointmentController {

  private final CreateAppointment createAppointment;
  private final GetAppointment getAppointment;
  private final UpdateAppointment updateAppointment;
  private final DeleteAppointment deleteAppointment;

  @PostMapping("/createAppointment")
  public ResponseEntity<Appointment> createAppointment(
      final @RequestBody @Valid Appointment appointment, final @RequestParam String cpf) {

    final var createdAppointment = createAppointment.execute(appointment, cpf);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdAppointment.getId())
            .toUri();

    return ResponseEntity.created(location).body(createdAppointment);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Appointment> findById(final @PathVariable int id) {

    return ResponseEntity.ok(getAppointment.execute(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Appointment> updateAppointment(
      final @PathVariable int id, final @RequestBody @Valid Appointment appointment) {

    Appointment updatedAppointment = updateAppointment.execute(id, appointment);
    return ResponseEntity.ok(updatedAppointment);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAppointment(final @PathVariable int id) {
    deleteAppointment.execute(id);

    return ResponseEntity.noContent().build();
  }

  @PostMapping("/notificationAppointment")
  public ResponseEntity<String> notificaCliente() {

    System.out.println("notificação enviada");

    return ResponseEntity.ok("Notificação enviada com sucesso");
  }
}
