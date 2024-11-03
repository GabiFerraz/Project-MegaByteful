package com.megabyteful.application.domain;

import jakarta.validation.constraints.Digits;
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
public class Service {
	
	private Integer id;
	private Integer idServiceProvider;
	
	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name length must be less than 100 characters")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Name must contain only letters,numbers and spaces")
	private String name;

	@NotBlank(message = "Price is required")
	@Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount with up to 10 digits and 2 decimal places")
	private Double price;
 
	public static Service createService(final Integer idServiceProvider, final String name, final Double price) {
		return new Service(null,idServiceProvider,name,price);
	}

}
