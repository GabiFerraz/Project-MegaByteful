package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.AppointmentTestFixture.appointmentToUpdate;
import static com.megabyteful.application.usecase.fixture.AppointmentTestFixture.validAppointment;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.usecase.exception.AppointmentNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class UpdateAppointmentTest {

  private final AppointmentGateway appointmentGateway = mock(AppointmentGateway.class);
  private final UpdateAppointment updateAppointment = new UpdateAppointment(appointmentGateway);

  @Test
  void shouldUpdateAppointmentSuccessfully() {
    final var id = 999;
    final var request = appointmentToUpdate();
    final var appointmentFoundRespGateway = validAppointment();
    final var appointmentUpdated = appointmentToUpdate();

    when(appointmentGateway.findById(id)).thenReturn(Optional.of(appointmentFoundRespGateway));
    when(appointmentGateway.update(any(Appointment.class))).thenReturn(appointmentUpdated);

    final var updatedAppointment = updateAppointment.execute(id, request);

    assertThat(updatedAppointment.getId()).isEqualTo(appointmentUpdated.getId());
    assertThat(updatedAppointment.getCustomerId()).isEqualTo(appointmentUpdated.getCustomerId());
    assertThat(updatedAppointment.getServiceProviderID())
        .isEqualTo(appointmentUpdated.getServiceProviderID());
    assertThat(updatedAppointment.getScheduleId()).isEqualTo(appointmentUpdated.getScheduleId());

    verify(appointmentGateway).findById(id);

    ArgumentCaptor<Appointment> captorAppointment = forClass(Appointment.class);

    verify(appointmentGateway).update(captorAppointment.capture());

    assertThat(captorAppointment.getValue().getId()).isEqualTo(request.getId());
    assertThat(captorAppointment.getValue().getCustomerId()).isEqualTo(request.getCustomerId());
    assertThat(captorAppointment.getValue().getServiceProviderID())
        .isEqualTo(request.getServiceProviderID());
    assertThat(captorAppointment.getValue().getScheduleId()).isEqualTo(request.getScheduleId());
  }

  @Test
  void shouldNotUpdateAppointmentWhenCpfDoesNotExist() {
    final var id = 999;
    final var request = appointmentToUpdate();

    when(appointmentGateway.findById(id)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> updateAppointment.execute(id, request))
        .isInstanceOf(AppointmentNotFoundException.class)
        .hasMessage("Appointment with Id [999] not found.");

    verify(appointmentGateway).findById(id);
    verify(appointmentGateway, never()).update(any());
  }
}
