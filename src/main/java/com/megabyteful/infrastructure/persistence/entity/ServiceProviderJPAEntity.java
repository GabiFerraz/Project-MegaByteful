package com.megabyteful.infrastructure.persistence.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import com.megabyteful.application.domain.ServiceProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "servicesProvider")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "UPDATE servicesProvider SET active = FALSE, deleted_at = NOW() WHERE id = ?")
@SQLRestriction(value = "active = TRUE")
public class ServiceProviderJPAEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name must be up to 100 characters")
	@Column(nullable = false, length = 100)
	private String name;
	
	@NotBlank(message = "Document is required")
	@Size(max = 14, message = "Document must be up to 14 characters")
	@Column(nullable = false, length = 14)
	private String document;
	
	@NotBlank(message = "Phone is required")
	@Size(max = 13, message = "Phone must be up to 13 characters")
	@Column(nullable = false, length = 13)
	private String phone;
	
	@NotBlank(message = "Beauty Services is required")
	@Size(max = 30, message = "Beauty Services must be up to 30 characters")
	@Column(nullable = false, length = 30)
	private String beautyServices;
	
	@NotBlank(message = "Address is required")
	@Size(max = 255, message = "Address must be up to 255 characters")
	@Column(nullable = false, length = 255)
	private String address;
	
	@NotBlank(message = "Email is required")
	@Size(max = 255, message = "Email must be up to 255 characters")
	@Column(nullable = false, length = 255)
	private String email;
	
	@Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    private Instant deletedAt;
    
    public ServiceProvider toServiceProvider() {
    	return ServiceProvider.with(id, name, document, phone, beautyServices, address, email);
    }
    
    public static ServiceProviderJPAEntity from(ServiceProvider serviceProvider) {
    	return ServiceProviderJPAEntity.builder()
    			.id(serviceProvider.getId())
    			.name(serviceProvider.getName())
    			.document(serviceProvider.getDocument())
    			.phone(serviceProvider.getPhone())
    			.beautyServices(serviceProvider.getBeautyServices())
    			.address(serviceProvider.getAddress())
    			.email(serviceProvider.getEmail())
    			.build();
    }  
    
	
}