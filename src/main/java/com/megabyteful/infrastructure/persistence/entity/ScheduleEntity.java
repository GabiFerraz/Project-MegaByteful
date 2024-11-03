package com.megabyteful.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "service_id", nullable = false)
  private ServiceEntity service;

  @OneToMany(mappedBy = "schedule")
  private List<AppointmentEntity> appointments;

  @Column(name = "service_time")
  private LocalDateTime serviceTime;

  @ElementCollection
  @CollectionTable(
      name = "schedule_available_times",
      joinColumns = @JoinColumn(name = "schedule_id"))
  @Column(name = "available_times")
  private List<String> availableTimes;
}
