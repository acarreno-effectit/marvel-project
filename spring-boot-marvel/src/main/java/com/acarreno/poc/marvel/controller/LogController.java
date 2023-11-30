package com.acarreno.poc.marvel.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acarreno.poc.marvel.model.Log;
import com.acarreno.poc.marvel.service.LogService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/log")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LogController {

  private final LogService logService;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<Log>> get() {
    return ResponseEntity.status(HttpStatus.OK).body(logService.getLogs());
  }

}
