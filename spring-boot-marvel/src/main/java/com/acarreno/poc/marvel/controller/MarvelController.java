package com.acarreno.poc.marvel.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acarreno.poc.marvel.model.Hero;
import com.acarreno.poc.marvel.model.HeroDetail;
import com.acarreno.poc.marvel.service.MarverService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/marvel")
public class MarvelController {

  private final MarverService marverService;

  @GetMapping(value = "/{characterId}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<HeroDetail> getByCharacterId(@PathVariable int characterId) {
    return ResponseEntity.status(HttpStatus.OK).body(marverService.getHeroeById(characterId));
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<Hero>> get() {
    return ResponseEntity.status(HttpStatus.OK).body(marverService.getHeroes());
  }

}
