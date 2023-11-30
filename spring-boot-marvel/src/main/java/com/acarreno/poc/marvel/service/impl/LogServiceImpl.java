package com.acarreno.poc.marvel.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.acarreno.poc.marvel.model.Log;
import com.acarreno.poc.marvel.persistence.entity.AuditServiceEntity;
import com.acarreno.poc.marvel.persistence.repository.AuditServiceRepository;
import com.acarreno.poc.marvel.service.LogService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

  private final AuditServiceRepository auditServiceRepository;

  @Override
  public List<Log> getLogs() {

    List<AuditServiceEntity> response = auditServiceRepository.findAll();

    if (response == null || response.isEmpty()) {
      return Collections.emptyList();
    }

    List<Log> result = response
        .stream().map(item -> Log.builder().createdDate(item.getCreatedDate())
            .service(item.getService()).codeResponse(item.getCodeResponse()).build())
        .collect(Collectors.toList());

    return result;
  }

}
