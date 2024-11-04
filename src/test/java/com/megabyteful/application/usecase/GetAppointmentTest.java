package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.AppointmentTestFixture.validAppointment;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.usecase.exception.AppointmentNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class GetAppointmentTest {

  private final AppointmentGateway appointmentGateway = mock(AppointmentGateway.class);
  private final GetAppointment getAppointment = new GetAppointment(appointmentGateway);

  @Test
  void shouldGetAppointmentSuccessfully() {
    final var id = 999;
    final var responseGateway = validAppointment();

    when(appointmentGateway.findById(id)).thenReturn(Optional.of(responseGateway));

    final var appointmentFound = getAppointment.execute(id);

    assertThat(appointmentFound.getId()).isEqualTo(responseGateway.getId());
    assertThat(appointmentFound.getScheduleId()).isEqualTo(responseGateway.getScheduleId());
    assertThat(appointmentFound.getServiceTime()).isEqualTo(responseGateway.getServiceTime());
    assertThat(appointmentFound.getCustomerId()).isEqualTo(responseGateway.getCustomerId());

    verify(appointmentGateway).findById(id);
  }

  @Test
  void shouldNotGetAppointmentWhenCpfDoesNotExist() {
    final var id = 999;

    when(appointmentGateway.findById(id)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> getAppointment.execute(id))
        .isInstanceOf(AppointmentNotFoundException.class)
        .hasMessage("Appointment with id [999] not found.");

    verify(appointmentGateway).findById(id);
  }
}
