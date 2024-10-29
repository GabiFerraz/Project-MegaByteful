package com.megabyteful.infrastructure.api;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.usecase.RegisterClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/megabyteful/customers")
public class CustomerController {

    private final RegisterClient registerClient;

    @PostMapping
    public ResponseEntity<Customer> createClient(
            final @RequestBody @Valid Customer customer) {
        return ResponseEntity.ok(registerClient.execute(customer));
    }
}
