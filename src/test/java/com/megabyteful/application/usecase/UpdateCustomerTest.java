package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.CustomerTestFixture.customerToUpdate;
import static com.megabyteful.application.usecase.fixture.CustomerTestFixture.validCustomer;
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
    final var request = customerToUpdate();
    final var customerFoundRespGateway = validCustomer();
    final var customerUpdated = customerToUpdate();

    when(customerGateway.findByCpf(cpf)).thenReturn(Optional.of(customerFoundRespGateway));
    when(customerGateway.update(any(Customer.class))).thenReturn(customerUpdated);

    final var updatedCustomer = updateCustomer.execute(cpf, request);

    assertThat(updatedCustomer.getName()).isEqualTo(customerUpdated.getName());
    assertThat(updatedCustomer.getCpf()).isEqualTo(customerUpdated.getCpf());
    assertThat(updatedCustomer.getPhone()).isEqualTo(customerUpdated.getPhone());
    assertThat(updatedCustomer.getEmail()).isEqualTo(customerUpdated.getEmail());

    verify(customerGateway).findByCpf(cpf);

    ArgumentCaptor<Customer> captorCustomer = forClass(Customer.class);

    verify(customerGateway).update(captorCustomer.capture());

    assertThat(captorCustomer.getValue().getName()).isEqualTo(request.getName());
    assertThat(captorCustomer.getValue().getCpf()).isEqualTo(request.getCpf());
    assertThat(captorCustomer.getValue().getPhone()).isEqualTo(request.getPhone());
    assertThat(captorCustomer.getValue().getEmail()).isEqualTo(request.getEmail());
  }

  @Test
  void shouldNotUpdateCustomerWhenCpfDoesNotExist() {
    final var cpf = "12345678901";
    final var request = customerToUpdate();

    when(customerGateway.findByCpf(cpf)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> updateCustomer.execute(cpf, request))
        .isInstanceOf(CustomerNotFoundException.class)
        .hasMessage("Customer with CPF [12345678901] not found.");

    verify(customerGateway).findByCpf(cpf);
    verify(customerGateway, never()).update(any());
  }
}
