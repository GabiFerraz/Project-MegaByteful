package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.dto.UpdateServiceProviderRequest;
import com.megabyteful.application.usecase.CreateServiceProvider;
import com.megabyteful.application.usecase.DeleteServiceProvider;
import com.megabyteful.application.usecase.GetServiceProvider;
import com.megabyteful.application.usecase.UpdateServiceProvider;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("megabyteful/servicesProviders")
public class ServiceProviderController {

  private final CreateServiceProvider createServiceProvider;
  private final DeleteServiceProvider deleteServiceProvider;
  private final GetServiceProvider getServiceProvider;
  private final UpdateServiceProvider updateServiceProvider;

  @PostMapping
  public ResponseEntity<ServiceProvider> create(
      @RequestBody @Valid ServiceProvider serviceProvider) {
    final var createdServiceProvider = createServiceProvider.execute(serviceProvider);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdServiceProvider.getId())
            .toUri();

    return ResponseEntity.created(location).body(createdServiceProvider);
  }

  @GetMapping("/{document}")
  public ResponseEntity<ServiceProvider> findByDocument(final @PathVariable String document) {
    return ResponseEntity.ok(getServiceProvider.execute(document));
  }

  @PutMapping("/{document}")
  public ResponseEntity<ServiceProvider> update(
      final @PathVariable String document,
      final @RequestBody @Valid UpdateServiceProviderRequest updateServiceProviderRequest) {
    return ResponseEntity.ok(updateServiceProvider.execute(document, updateServiceProviderRequest));
  }

  @DeleteMapping("/{document}")
  public ResponseEntity<Void> delete(final @PathVariable String document) {
    deleteServiceProvider.execute(document);
    return ResponseEntity.noContent().build();
  }
}
