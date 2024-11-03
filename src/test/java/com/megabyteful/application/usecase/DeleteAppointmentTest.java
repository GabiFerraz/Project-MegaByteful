package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.AppointmentTestFixture.validAppointmentResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.usecase.exception.AppointmentNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class DeleteAppointmentTest {

  private final AppointmentGateway appointmentGateway = mock(AppointmentGateway.class);
  private final DeleteAppointment deleteAppointment = new DeleteAppointment(appointmentGateway);

  @Test
  void shouldDeleteAppointmentSuccessfully() {
    final var id = 1;
    final var responseGateway = validAppointmentResponse();

    when(appointmentGateway.findById(id)).thenReturn(Optional.of(responseGateway));
    doNothing().when(appointmentGateway).delete(id);

    deleteAppointment.execute(id);

    verify(appointmentGateway).findById(id);
    verify(appointmentGateway).delete(id);
  }

  @Test
  void shouldNotDeleteAppointmentWhenCpfDoesNotExist() {
    final var id = 1;

    when(appointmentGateway.findById(id)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> deleteAppointment.execute(id))
        .isInstanceOf(AppointmentNotFoundException.class)
        .hasMessage("Appointment with id [1] not found.");

    verify(appointmentGateway).findById(id);
    verify(appointmentGateway, never()).delete(id);
  }
}
