package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.ServiceProviderTestFixture.validServiceProviderRequest;
import static com.megabyteful.application.usecase.fixture.ServiceProviderTestFixture.validServiceProviderResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.exception.ServiceProviderAlreadyExistsException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class CreateServiceProviderTest {

  private final ServiceProviderGateway serviceProviderGateway = mock(ServiceProviderGateway.class);
  private final CreateServiceProvider createServiceProvider =
      new CreateServiceProvider(serviceProviderGateway);

  @Test
  void shouldCreateServiceProviderSuccessfuly() {
    final var request = validServiceProviderRequest();
    final var gatewayResponse = validServiceProviderResponse();

    when(serviceProviderGateway.findByDocument(request.getDocument())).thenReturn(Optional.empty());
    when(serviceProviderGateway.save(any(ServiceProvider.class))).thenReturn(gatewayResponse);

    final var createdServiceProvider = createServiceProvider.execute(request);

    assertThat(createdServiceProvider.getName()).isEqualTo(request.getName());
    assertThat(createdServiceProvider.getDocument()).isEqualTo(request.getDocument());
    assertThat(createdServiceProvider.getPhone()).isEqualTo(request.getPhone());
    assertThat(createdServiceProvider.getBeautyServices()).isEqualTo(request.getBeautyServices());
    assertThat(createdServiceProvider.getAddress()).isEqualTo(request.getAddress());
    assertThat(createdServiceProvider.getEmail()).isEqualTo(request.getEmail());

    verify(serviceProviderGateway).findByDocument(any());

    ArgumentCaptor<ServiceProvider> captorServiceProvider = forClass(ServiceProvider.class);

    verify(serviceProviderGateway).save(captorServiceProvider.capture());

    assertThat(captorServiceProvider.getValue().getName()).isEqualTo(request.getName());
    assertThat(captorServiceProvider.getValue().getDocument()).isEqualTo(request.getDocument());
    assertThat(captorServiceProvider.getValue().getPhone()).isEqualTo(request.getPhone());
    assertThat(createdServiceProvider.getBeautyServices()).isEqualTo(request.getBeautyServices());
    assertThat(createdServiceProvider.getAddress()).isEqualTo(request.getAddress());
    assertThat(captorServiceProvider.getValue().getEmail()).isEqualTo(request.getEmail());
  }

  @Test
  void shouldNotCreateCustomerWhenCpfAlreadyExists() {
    final var request = validServiceProviderRequest();
    final var gatewayResponse = validServiceProviderResponse();

    when(serviceProviderGateway.findByDocument(request.getDocument()))
        .thenReturn(Optional.of(gatewayResponse));

    assertThatThrownBy(() -> createServiceProvider.execute(request))
        .isInstanceOf(ServiceProviderAlreadyExistsException.class)
        .hasMessage("Service Provider [Maria Silva] with CPF [12345678900] already exists.");

    verify(serviceProviderGateway).findByDocument(any());
    verify(serviceProviderGateway, never()).save(request);
  }
}
