package com.megabyteful.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "serviceProvider")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServiceProviderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "cpf", unique = true, nullable = false)
	private String document;
	
	
	@Column(name = "phone")
	private String phone;
	
	
	@Column(name = "beautyServices")
	private String beautyServices;
	
	
	@Column(name = "address")
	private String address;
	
	
	@Column(name = "email")
	private String email;
	
	
}