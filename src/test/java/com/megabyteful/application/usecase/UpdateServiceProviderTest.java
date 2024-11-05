package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.ServiceProviderTestFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.exception.ServiceProviderNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class ServiceProviderTest {

  private final ServiceProviderGateway serviceProviderGateway = mock(ServiceProviderGateway.class);
  private final UpdateServiceProvider updateServiceProvider =
      new UpdateServiceProvider(serviceProviderGateway);

  @Test
  void shouldUpdateServiceProviderSuccessfully() {
    final var document = "12345678900";
    final var request = serviceProviderUpdateRequest();
    final var serviceProviderFoundRespGateway = validServiceProviderResponse();
    final var serviceProviderUpdatedRespGateway = validUpdateServiceProviderResponse();

    when(serviceProviderGateway.findByDocument(document))
        .thenReturn(Optional.of(serviceProviderFoundRespGateway));
    when(serviceProviderGateway.update(any(ServiceProvider.class)))
        .thenReturn(serviceProviderUpdatedRespGateway);

    final var responseUsecase = updateServiceProvider.execute(document, request);

    assertThat(responseUsecase.getId()).isEqualTo(serviceProviderUpdatedRespGateway.getId());
    assertThat(responseUsecase.getName()).isEqualTo(serviceProviderUpdatedRespGateway.getName());
    assertThat(responseUsecase.getDocument())
        .isEqualTo(serviceProviderUpdatedRespGateway.getDocument());
    assertThat(responseUsecase.getPhone()).isEqualTo(serviceProviderUpdatedRespGateway.getPhone());
    assertThat(responseUsecase.getBeautyServices())
        .isEqualTo(serviceProviderUpdatedRespGateway.getBeautyServices());
    assertThat(responseUsecase.getAddress())
        .isEqualTo(serviceProviderUpdatedRespGateway.getAddress());
    assertThat(responseUsecase.getEmail()).isEqualTo(serviceProviderUpdatedRespGateway.getEmail());

    verify(serviceProviderGateway).findByDocument(document);

    ArgumentCaptor<ServiceProvider> captorCustomer = forClass(ServiceProvider.class);

    verify(serviceProviderGateway).update(captorCustomer.capture());

    assertThat(captorCustomer.getValue().getId())
        .isEqualTo(serviceProviderFoundRespGateway.getId());
    assertThat(captorCustomer.getValue().getName()).isEqualTo(request.getName());
    assertThat(captorCustomer.getValue().getDocument())
        .isEqualTo(serviceProviderUpdatedRespGateway.getDocument());
    assertThat(captorCustomer.getValue().getPhone()).isEqualTo(request.getPhone());
    assertThat(captorCustomer.getValue().getBeautyServices())
        .isEqualTo(request.getBeautyServices());
    assertThat(captorCustomer.getValue().getAddress()).isEqualTo(request.getAddress());
    assertThat(captorCustomer.getValue().getEmail()).isEqualTo(request.getEmail());
  }

  @Test
  void shouldNotUpdateServiceProviderWhenDocumentDoesNotExist() {
    final var document = "12345678900";
    final var request = serviceProviderUpdateRequest();

    when(serviceProviderGateway.findByDocument(document)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> updateServiceProvider.execute(document, request))
        .isInstanceOf(ServiceProviderNotFoundException.class)
        .hasMessage("Service Provider with document [12345678900] not found.");

    verify(serviceProviderGateway).findByDocument(document);
    verify(serviceProviderGateway, never()).update(any());
  }
}
