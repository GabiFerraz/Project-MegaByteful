package com.megabyteful.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Validated
public class UpdateServiceProviderDTO {

	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("name")
	private String name;

	@JsonProperty("document")
	private String document;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("beautyServices")
	private String beautyServices;

	@JsonProperty("address")
	private String address;

	@JsonProperty("email")
	private String email;

	public UpdateServiceProviderDTO id(Long id) {
		this.id = id;
		return this;
	}

	@Schema(example = "1", description = "The ID of the service provider")
	@NotNull

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name
	 */
	public UpdateServiceProviderDTO name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param document
	 */
	public UpdateServiceProviderDTO document (String document) {
		this.document = document;
		return this;
	}

	/**
	 * @return the document
	 */
	public String getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(String document) {
		this.document = document;
	}

	/**
	 * @param phone
	 */
	public UpdateServiceProviderDTO phone (String phone) {
		this.phone = phone;
		return this;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param beautyServices
	 */
	public UpdateServiceProviderDTO beautyServices(String beautyServices) {
		this.beautyServices = beautyServices;
		return this;
	}

	/**
	 * @return the beautyServices
	 */
	public String getBeautyServices() {
		return beautyServices;
	}

	/**
	 * @param beautyServices the beautyServices to set
	 */
	public void setBeautyServices(String beautyServices) {
		this.beautyServices = beautyServices;
	}

	/**
	 * @param address
	 */
	public UpdateServiceProviderDTO address(String address) {
		this.address = address;
		return this;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param email
	 */
	public UpdateServiceProviderDTO email (String email) {
		this.email = email;
		return this;
	}
	
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, beautyServices, document, email, id, name, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateServiceProviderDTO other = (UpdateServiceProviderDTO) obj;
		return Objects.equals(address, other.address) && Objects.equals(beautyServices, other.beautyServices)
				&& Objects.equals(document, other.document) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone);
	}

	@Override
	public String toString() {
		return "UpdateServiceProviderDTO [id=" + id + ", name=" + name + ", document=" + document + ", phone=" + phone
				+ ", beautyServices=" + beautyServices + ", address=" + address + ", email=" + email + "]";
	}

	
}
