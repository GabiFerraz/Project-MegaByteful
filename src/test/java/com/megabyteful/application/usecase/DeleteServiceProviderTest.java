package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.ServiceProviderTestFixture.validServiceProviderResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.exception.ServiceProviderNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class DeleteServiceProviderTest {

  private final ServiceProviderGateway serviceProviderGateway = mock(ServiceProviderGateway.class);
  private final DeleteServiceProvider deleteServiceProvider =
      new DeleteServiceProvider(serviceProviderGateway);

  @Test
  void shouldDeleteServiceProviderSuccessfully() {
    final var document = "12345678900";
    final var responseGateway = validServiceProviderResponse();

    when(serviceProviderGateway.findByDocument(document)).thenReturn(Optional.of(responseGateway));
    doNothing().when(serviceProviderGateway).delete(document);

    deleteServiceProvider.execute(document);

    verify(serviceProviderGateway).findByDocument(document);
    verify(serviceProviderGateway).delete(document);
  }

  @Test
  void shouldNotDeleteCustomerWhenCpfDoesNotExist() {
    final var document = "12345678900";

    when(serviceProviderGateway.findByDocument(document)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> deleteServiceProvider.execute(document))
        .isInstanceOf(ServiceProviderNotFoundException.class)
        .hasMessage("Service Provider with document [12345678900] not found.");

    verify(serviceProviderGateway).findByDocument(document);
    verify(serviceProviderGateway, never()).delete(document);
  }
}
