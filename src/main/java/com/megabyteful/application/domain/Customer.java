package com.megabyteful.application.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

  @NotBlank(message = "Name is required")
  @Size(max = 100, message = "Name length must be less than 100 characters")
  @Pattern(regexp = "[a-zA-Z\\s]+", message = "Name must contain only letters and spaces")
  private String name;

  @NotBlank(message = "CPF is required")
  @Pattern(regexp = "\\d{11}", message = "CPF must be 11 digits")
  private String cpf;

  @NotBlank(message = "Phone is required")
  @Pattern(regexp = "\\+?\\d{10,15}", message = "Phone must be between 10 and 15 digits")
  private String phone;

  @NotBlank(message = "Email is required")
  @Size(max = 255, message = "Email length must be less than 255 characters")
  @Email(message = "Email should be valid")
  private String email;

  public static Customer createCustomer(
      final String name, final String cpf, final String phone, final String email) {

    return new Customer(name, cpf, phone, email);
  }
}
