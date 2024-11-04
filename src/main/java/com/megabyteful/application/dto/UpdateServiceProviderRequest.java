package com.megabyteful.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateServiceProviderRequest {

  @NotBlank(message = "Name is required")
  @Size(max = 100, message = "Name length must be less than 100 characters")
  @Pattern(regexp = "[a-zA-Z\\s]+", message = "Name must contain only letters and spaces")
  private String name;

  @NotBlank(message = "Phone is required")
  @Pattern(regexp = "\\+?\\d{10,15}", message = "Phone must be between 10 and 15 digits")
  private String phone;

  @NotBlank(message = "Beauty Service is required")
  @Size(max = 100, message = "Beauty Service length must be less than 30 characters")
  private String beautyServices;

  @NotBlank(message = "Address is required")
  @Size(max = 100, message = "Address length must be less than 255 characters")
  private String address;

  @NotBlank(message = "Email is required")
  @Size(max = 255, message = "Email length must be less than 255 characters")
  @Email(message = "Email should be valid")
  private String email;
}