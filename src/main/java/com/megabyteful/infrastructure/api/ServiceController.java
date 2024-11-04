package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Service;
import com.megabyteful.application.dto.UpdateServiceRequest;
import com.megabyteful.application.usecase.CreateService;
import com.megabyteful.application.usecase.DeleteService;
import com.megabyteful.application.usecase.GetService;
import com.megabyteful.application.usecase.UpdateService;
import java.net.URI;

import javax.management.ServiceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
<<<<<<< HEAD
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

  @GetMapping("/{name}/{idProviderService}") 
  public ResponseEntity<Service> findByNameAndIdProviderService(final @PathVariable String name, final @PathVariable Integer idProviderService) throws ServiceNotFoundException {  	  
    return ResponseEntity.ok(getService.execute(name,idProviderService));
  }

  @PutMapping("/{name}/{idProviderService}")
  public ResponseEntity<Service> update(
      final @PathVariable String name,
      final @PathVariable Integer idProviderService,
      final @RequestBody UpdateServiceRequest updateServiceRequest) throws ServiceNotFoundException {

    return ResponseEntity.ok(updateService.execute(name, idProviderService, updateServiceRequest));
  }

  @DeleteMapping("/{name}/{idProviderService}")
  public ResponseEntity<Integer> delete(final @PathVariable String name,final @PathVariable Integer idProviderService) throws ServiceNotFoundException {
    deleteService.execute(name,idProviderService);

    return ResponseEntity.noContent().build();
  }
}
=======
@RequestMapping("/api/services")
public class ServiceController {}
>>>>>>> a283ff667951951f936e4fa5379dfa01f4b06855
