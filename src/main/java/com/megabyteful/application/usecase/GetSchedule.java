package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Schedule;
import com.megabyteful.application.gateway.ScheduleGateway;
import com.megabyteful.application.usecase.exception.ScheduleNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetSchedule {

  private final ScheduleGateway gateway;

  public Schedule execute(final LocalDateTime serviceTime) {
    return gateway
        .findByServiceTime(serviceTime)
        .orElseThrow(() -> new ScheduleNotFoundException(serviceTime));
  }
}
