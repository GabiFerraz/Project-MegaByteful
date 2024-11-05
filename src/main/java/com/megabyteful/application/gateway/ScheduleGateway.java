package com.megabyteful.application.gateway;

import com.megabyteful.application.domain.Schedule;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ScheduleGateway {

  Schedule save(final Schedule schedule);

  Optional<Schedule> findByServiceTime(final LocalDateTime serviceTime);

  Schedule update(final Schedule schedule);

  void delete(final LocalDateTime serviceTime);
}
