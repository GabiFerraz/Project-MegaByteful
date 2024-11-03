package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Schedule;
import com.megabyteful.application.gateway.ScheduleGateway;
import com.megabyteful.application.usecase.exception.ScheduleAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateSchedule {

  private final ScheduleGateway gateway;

  public Schedule execute(final Schedule request) {
    final var scheduleFound = gateway.findByServiceTime(request.getServiceTime());

    if (scheduleFound.isPresent()) {
      throw new ScheduleAlreadyExistsException(request.getServiceTime());
    }

    final var buildDomain =
        Schedule.createSchedule(
            request.getServiceId(),
            request.getAppointments(),
            request.getServiceTime(),
            request.getAvailableTimes());

    return gateway.save(buildDomain);
  }
}
