package com.megabyteful.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "cpf", unique = true, nullable = false)
  private String cpf;

  @Column(name = "phone")
  private String phone;

  @Column(name = "email")
  private String email;

  @OneToMany(mappedBy = "customer")
  private List<AppointmentEntity> appointments;
}
