package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Schedule;
import com.megabyteful.application.usecase.CreateSchedule;
import com.megabyteful.application.usecase.GetSchedule;
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
}
