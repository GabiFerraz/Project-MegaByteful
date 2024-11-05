package com.megabyteful.application.usecase;

import com.megabyteful.application.gateway.ScheduleGateway;
import com.megabyteful.application.usecase.exception.ScheduleNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteSchedule {

  private final ScheduleGateway gateway;

  public void execute(final LocalDateTime serviceTime) {
    final var schedule =
        gateway
            .findByServiceTime(serviceTime)
            .orElseThrow(() -> new ScheduleNotFoundException(serviceTime));

    gateway.delete(schedule.getServiceTime());
  }
}
