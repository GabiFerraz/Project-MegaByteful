package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.AppointmentTestFixture.validAppointment;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.usecase.exception.AppointmentAlreadyExistsException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class CreateAppointmentTest {

  private final AppointmentGateway appointmentGateway = mock(AppointmentGateway.class);
  private final CreateAppointment createAppointment = new CreateAppointment(appointmentGateway);

  @Test
  void shouldCreateAppointmentSuccessfully() {
    final var cpf = "12345678901";
    final var appointmentRequest = validAppointment();
    final var gatewayResponse = validAppointment();

    when(appointmentGateway.existsByScheduleCustomerAndTime(
            any(Integer.class), any(Integer.class), any(LocalDateTime.class)))
        .thenReturn(false);
    when(appointmentGateway.save(any(Appointment.class), eq(cpf))).thenReturn(gatewayResponse);

    final var createdAppointment = createAppointment.execute(appointmentRequest, cpf);

    assertThat(createdAppointment.getId()).isEqualTo(appointmentRequest.getId());
    assertThat(createdAppointment.getCustomerId()).isEqualTo(appointmentRequest.getCustomerId());
    assertThat(createdAppointment.getScheduleId()).isEqualTo(appointmentRequest.getScheduleId());
    assertThat(createdAppointment.getServiceTime()).isNotNull();

    verify(appointmentGateway)
        .existsByScheduleCustomerAndTime(
            eq(appointmentRequest.getCustomerId()),
            eq(appointmentRequest.getScheduleId()),
            eq(appointmentRequest.getServiceTime()));

    ArgumentCaptor<Appointment> captorAppointment = forClass(Appointment.class);

    verify(appointmentGateway).save(captorAppointment.capture(), eq(cpf));

    assertThat(captorAppointment.getValue().getId()).isEqualTo(appointmentRequest.getId());
    assertThat(captorAppointment.getValue().getCustomerId())
        .isEqualTo(appointmentRequest.getCustomerId());
    assertThat(captorAppointment.getValue().getScheduleId())
        .isEqualTo(appointmentRequest.getScheduleId());
    assertThat(captorAppointment.getValue().getServiceTime()).isNotNull();
  }

  @Test
  void shouldNotCreateCustomerWhenCpfAlreadyExists() {
    final var cpf = "12345678901";
    final var appointmentRequest = validAppointment();

    when(appointmentGateway.existsByScheduleCustomerAndTime(
            eq(appointmentRequest.getCustomerId()),
            eq(appointmentRequest.getScheduleId()),
            eq(appointmentRequest.getServiceTime())))
        .thenReturn(true);

    assertThatThrownBy(() -> createAppointment.execute(appointmentRequest, cpf))
        .isInstanceOf(AppointmentAlreadyExistsException.class)
        .hasMessage("Appointment with schedule id [1] already exists.");

    verify(appointmentGateway)
        .existsByScheduleCustomerAndTime(
            eq(appointmentRequest.getCustomerId()),
            eq(appointmentRequest.getScheduleId()),
            eq(appointmentRequest.getServiceTime()));
    verify(appointmentGateway, never()).save(any(), any());
  }
}
