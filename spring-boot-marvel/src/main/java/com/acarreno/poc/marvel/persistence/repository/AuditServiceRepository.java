package com.acarreno.poc.marvel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acarreno.poc.marvel.persistence.entity.AuditServiceEntity;

public interface AuditServiceRepository extends JpaRepository<AuditServiceEntity, String> {

}
