package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Schedule;
import com.megabyteful.application.dto.UpdateScheduleRequest;
import com.megabyteful.application.gateway.ScheduleGateway;
import com.megabyteful.application.usecase.exception.ScheduleNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateSchedule {

  private final ScheduleGateway scheduleGateway;

  public Schedule execute(final LocalDateTime serviceTime, final UpdateScheduleRequest request) {
    final var scheduleFound =
        scheduleGateway
            .findByServiceTime(serviceTime)
            .orElseThrow(() -> new ScheduleNotFoundException(request.getServiceTime()));

    scheduleFound.setServiceTime(request.getServiceTime());
    scheduleFound.setAvailableTimes(request.getAvailableTimes());

    return scheduleGateway.update(scheduleFound);
  }
}
