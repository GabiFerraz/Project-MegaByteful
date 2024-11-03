package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.ServiceProviderTestFixture.validServiceProviderResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.exception.ServiceProviderNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class GetServiceProviderTest {

	private final ServiceProviderGateway serviceProviderGateway = mock(ServiceProviderGateway.class);
	private final GetServiceProvider getServiceProvider = new GetServiceProvider(serviceProviderGateway);

	  @Test
	  void shouldGetServiceProviderSuccessfully() {
	    final var document = "12345678900";
	    final var responseGateway = validServiceProviderResponse();

	    when(serviceProviderGateway.findByDocument(document)).thenReturn(Optional.of(responseGateway));

	    final var serviceProviderFound = getServiceProvider.execute(document);

	    assertThat(serviceProviderFound.getId()).isEqualTo(responseGateway.getId());
	    assertThat(serviceProviderFound.getName()).isEqualTo(responseGateway.getName());
	    assertThat(serviceProviderFound.getDocument()).isEqualTo(responseGateway.getDocument());
	    assertThat(serviceProviderFound.getPhone()).isEqualTo(responseGateway.getPhone());
	    assertThat(serviceProviderFound.getBeautyServices()).isEqualTo(responseGateway.getBeautyServices());
	    assertThat(serviceProviderFound.getAddress()).isEqualTo(responseGateway.getAddress());
	    assertThat(serviceProviderFound.getEmail()).isEqualTo(responseGateway.getEmail());
	    
	    
	    verify(serviceProviderGateway).findByDocument(document);
	  }

	  @Test
	  void shouldNotGetCustomerWhenCpfDoesNotExist() {
	    final var document = "12345678901";

	    when(serviceProviderGateway.findByDocument(document)).thenReturn(Optional.empty());

	    assertThatThrownBy(() -> getServiceProvider.execute(document))
	        .isInstanceOf(ServiceProviderNotFoundException.class)
	        .hasMessage("Service Provider with Document [12345678900] not found.");

	    verify(serviceProviderGateway).findByDocument(document);
	  }
}
