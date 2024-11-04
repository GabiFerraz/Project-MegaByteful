package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Service;
import com.megabyteful.application.dto.UpdateServiceRequest;
import com.megabyteful.application.usecase.CreateService;
import com.megabyteful.application.usecase.DeleteService;
import com.megabyteful.application.usecase.GetService;
import com.megabyteful.application.usecase.UpdateService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/megabyteful/services")
public class ServiceController {

  private final CreateService createService;
  private final GetService getService;
  private final UpdateService updateService;
  private final DeleteService deleteService;

  @PostMapping
  public ResponseEntity<Service> create(final @RequestBody Service service) {
    final var createdService = createService.execute(service);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdService.getId())
            .toUri();

    return ResponseEntity.created(location).body(createdService);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Service> findById(final @PathVariable int id) {

    return ResponseEntity.ok(getService.execute(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Service> update(
      final @PathVariable int id, final @RequestBody UpdateServiceRequest updateServiceRequest) {

    return ResponseEntity.ok(updateService.execute(id, updateServiceRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(final @PathVariable int id) {
    deleteService.execute(id);

    return ResponseEntity.noContent().build();
  }
}
