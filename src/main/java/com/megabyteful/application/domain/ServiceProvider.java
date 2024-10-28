package com.megabyteful.application.domain;

import lombok.Getter;

@Getter
public class ServiceProvider {
    private Long id;
    private String name;
    private String document;
    private String phone;
    private String beautyServices;
    private String address;
    private String email;
    
    private ServiceProvider(final String name, final String document, final String phone, final String beautyServices, final String address, final String email) {
		this.name = name;
		this.document = document;
		this.phone = phone;
		this.beautyServices = beautyServices;
		this.address = address;
		this.email = email;
	}
	
	private ServiceProvider(final Long id, final String name,final String document, final String phone, final String beautyServices, final String address, final String email) {
		this(name, document, phone, beautyServices, address, email);
		this.id = id;
	}
	
	public static ServiceProvider newServiceProvider(final String name, final String document, final String phone, final String beautyServices, final String address, final String email) {
		return new ServiceProvider(name, document, phone, beautyServices, address, email);
	}
	
	public ServiceProvider update(final String name, final String document, final String phone, final String beautyServices, final String address, final String email) {
		this.name = name;
		this.document = document;
		this.phone = phone;
		this.beautyServices = beautyServices;
		this.address = address;
		this.email = email;
		return this;
	}
	
	public static ServiceProvider with (final Long id, final String name, final String document, final String phone, final String beautyServices, final String address, final String email) {
		return new ServiceProvider(id, name, document, phone, beautyServices, address, email);
	}
}
