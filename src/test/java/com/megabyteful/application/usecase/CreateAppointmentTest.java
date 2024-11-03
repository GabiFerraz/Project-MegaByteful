package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.AppointmentTestFixture.validAppointment;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.usecase.exception.CustomerAlreadyExistsException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class CreateAppointmentTest {

  private final AppointmentGateway appointmentGateway = mock(AppointmentGateway.class);
  private final CreateAppointment createAppointment = new CreateAppointment(appointmentGateway);

  @Test
  void shouldCreateAppointmentSuccessfully() {
    final var appointmentRequest = validAppointment();
    final var gatewayResponse = validAppointment();

    when(appointmentGateway.findById(appointmentRequest.getId())).thenReturn(Optional.empty());
    when(appointmentGateway.save(any(Appointment.class))).thenReturn(gatewayResponse);

    final var createdAppointment = createAppointment.execute(appointmentRequest);

    assertThat(createdAppointment.getId()).isEqualTo(appointmentRequest.getId());
    assertThat(createdAppointment.getCustomerId()).isEqualTo(appointmentRequest.getCustomerId());
    assertThat(createdAppointment.getScheduleId()).isEqualTo(appointmentRequest.getScheduleId());
    assertThat(createdAppointment.getServiceProviderId())
        .isEqualTo(appointmentRequest.getServiceProviderId());

    verify(appointmentGateway).findById(any());

    ArgumentCaptor<Appointment> captorAppointment = forClass(Appointment.class);

    verify(appointmentGateway).save(captorAppointment.capture());

    assertThat(captorAppointment.getValue().getId()).isEqualTo(appointmentRequest.getId());
    assertThat(captorAppointment.getValue().getCustomerId())
        .isEqualTo(appointmentRequest.getCustomerId());
    assertThat(captorAppointment.getValue().getScheduleId())
        .isEqualTo(appointmentRequest.getScheduleId());
    assertThat(captorAppointment.getValue().getServiceProviderId())
        .isEqualTo(appointmentRequest.getServiceProviderId());
  }

  @Test
  void shouldNotCreateCustomerWhenCpfAlreadyExists() {
    final var appointmentRequest = validAppointment();
    final var gatewayResponse = validAppointment();

    when(appointmentGateway.findById(appointmentRequest.getId()))
        .thenReturn(Optional.of(gatewayResponse));

    assertThatThrownBy(() -> createAppointment.execute(appointmentRequest))
        .isInstanceOf(CustomerAlreadyExistsException.class)
        .hasMessage("Appointment with Id [1] already exists.");

    verify(appointmentGateway).findById(any());
    verify(appointmentGateway, never()).save(appointmentRequest);
  }
}
