package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Schedule;
import com.megabyteful.application.dto.UpdateScheduleRequest;
import com.megabyteful.application.usecase.CreateSchedule;
import com.megabyteful.application.usecase.DeleteSchedule;
import com.megabyteful.application.usecase.GetSchedule;
import com.megabyteful.application.usecase.UpdateSchedule;
import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/megabyteful/schedules")
public class ScheduleController {

  private final CreateSchedule createSchedule;
  private final GetSchedule getSchedule;
  private final UpdateSchedule updateSchedule;
  private final DeleteSchedule deleteSchedule;

  @PostMapping
  public ResponseEntity<Schedule> create(final @RequestBody @Valid Schedule schedule) {
    final var createdSchedule = createSchedule.execute(schedule);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdSchedule.getId())
            .toUri();

    return ResponseEntity.created(location).body(createdSchedule);
  }

  @GetMapping("/{serviceTime}")
  public ResponseEntity<Schedule> findByServiceTime(
      final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime
              serviceTime) {

    return ResponseEntity.ok(getSchedule.execute(serviceTime));
  }

  @PutMapping("/{serviceTime}")
  public ResponseEntity<Schedule> update(
      final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime
              serviceTime,
      final @RequestBody @Valid UpdateScheduleRequest request) {

    return ResponseEntity.ok(updateSchedule.execute(serviceTime, request));
  }

  @DeleteMapping("/{serviceTime}")
  public ResponseEntity<Void> delete(
      final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime
              serviceTime) {
    deleteSchedule.execute(serviceTime);

    return ResponseEntity.noContent().build();
  }
}
