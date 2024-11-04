package com.megabyteful.application.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateServiceRequest {

  @NotBlank(message = "Name is required")
  @Size(max = 100, message = "Name length must be less than 100 characters")
  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Name must contain only letters,numbers and spaces")
  private String name;

  @NotBlank(message = "Price is required")
  @Digits(
      integer = 10,
      fraction = 2,
      message = "Price must be a valid monetary amount with up to 10 digits and 2 decimal places")
  private Double price;
}
