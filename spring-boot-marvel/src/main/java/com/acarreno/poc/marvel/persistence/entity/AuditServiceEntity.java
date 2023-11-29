package com.acarreno.poc.marvel.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "log", name = "service")
public class AuditServiceEntity {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = "id", nullable = false, unique = true)
  private UUID id;

  @Column(name = "service", nullable = true, unique = false)
  private String service;

  @Column(name = "code_response", nullable = true, unique = false)
  private String codeResponse;

  @CreationTimestamp
  @Column(name = "created_date", nullable = false, unique = false)
  private LocalDateTime createdDate;

}
