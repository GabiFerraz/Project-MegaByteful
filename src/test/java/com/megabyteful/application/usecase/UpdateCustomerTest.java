package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.CustomerTestFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class UpdateCustomerTest {

  private final CustomerGateway customerGateway = mock(CustomerGateway.class);
  private final UpdateCustomer updateCustomer = new UpdateCustomer(customerGateway);

  @Test
  void shouldUpdateCustomerSuccessfully() {
    final var cpf = "12345678901";
    final var request = customerUpdateRequest();
    final var customerFoundRespGateway = validCustomerResponse();
    final var customerUpdatedRespGateway = validUpdatedCustomerResponse();

    when(customerGateway.findByCpf(cpf)).thenReturn(Optional.of(customerFoundRespGateway));
    when(customerGateway.update(any(Customer.class))).thenReturn(customerUpdatedRespGateway);

    final var responseUsecase = updateCustomer.execute(cpf, request);

    assertThat(responseUsecase.getId()).isEqualTo(customerUpdatedRespGateway.getId());
    assertThat(responseUsecase.getName()).isEqualTo(customerUpdatedRespGateway.getName());
    assertThat(responseUsecase.getCpf()).isEqualTo(customerUpdatedRespGateway.getCpf());
    assertThat(responseUsecase.getPhone()).isEqualTo(customerUpdatedRespGateway.getPhone());
    assertThat(responseUsecase.getEmail()).isEqualTo(customerUpdatedRespGateway.getEmail());

    verify(customerGateway).findByCpf(cpf);

    ArgumentCaptor<Customer> captorCustomer = forClass(Customer.class);

    verify(customerGateway).update(captorCustomer.capture());

    assertThat(captorCustomer.getValue().getId()).isEqualTo(customerFoundRespGateway.getId());
    assertThat(captorCustomer.getValue().getName()).isEqualTo(request.getName());
    assertThat(captorCustomer.getValue().getCpf()).isEqualTo(customerUpdatedRespGateway.getCpf());
    assertThat(captorCustomer.getValue().getPhone()).isEqualTo(request.getPhone());
    assertThat(captorCustomer.getValue().getEmail()).isEqualTo(request.getEmail());
  }

  @Test
  void shouldNotUpdateCustomerWhenCpfDoesNotExist() {
    final var cpf = "12345678901";
    final var request = customerUpdateRequest();

    when(customerGateway.findByCpf(cpf)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> updateCustomer.execute(cpf, request))
        .isInstanceOf(CustomerNotFoundException.class)
        .hasMessage("Customer with CPF [12345678901] not found.");

    verify(customerGateway).findByCpf(cpf);
    verify(customerGateway, never()).update(any());
  }
}
