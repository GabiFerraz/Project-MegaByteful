package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.dto.UpdateCustomerRequest;
import com.megabyteful.application.usecase.CreateCustomer;
import com.megabyteful.application.usecase.DeleteCustomer;
import com.megabyteful.application.usecase.GetCustomer;
import com.megabyteful.application.usecase.UpdateCustomer;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/megabyteful/customers")
public class CustomerController {

  private final CreateCustomer createCustomer;
  private final GetCustomer getCustomer;
  private final UpdateCustomer updateCustomer;
  private final DeleteCustomer deleteCustomer;

  @PostMapping
  public ResponseEntity<Customer> create(final @RequestBody @Valid Customer customer) {
    final var createdCustomer = createCustomer.execute(customer);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdCustomer.getId())
            .toUri();

    return ResponseEntity.created(location).body(createdCustomer);
  }

  @GetMapping("/{cpf}")
  public ResponseEntity<Customer> findByCpf(final @PathVariable String cpf) {

    return ResponseEntity.ok(getCustomer.execute(cpf));
  }

  @PutMapping("/{cpf}")
  public ResponseEntity<Customer> update(
      final @PathVariable String cpf,
      final @RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {

    return ResponseEntity.ok(updateCustomer.execute(cpf, updateCustomerRequest));
  }

  @DeleteMapping("/{cpf}")
  public ResponseEntity<Void> delete(final @PathVariable String cpf) {
    deleteCustomer.execute(cpf);

    return ResponseEntity.noContent().build();
  }
}
