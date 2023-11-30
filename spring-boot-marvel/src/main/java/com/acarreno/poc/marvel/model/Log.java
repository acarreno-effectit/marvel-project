package com.acarreno.poc.marvel.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Log {

  private String service;
  private String codeResponse;
  private LocalDateTime createdDate;

}
