package com.megabyteful.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private Double price;

  @OneToMany(mappedBy = "service")
  private List<ScheduleEntity> schedules;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "service_provider_id", nullable = false)
  private ServiceProviderEntity serviceProvider;
}
