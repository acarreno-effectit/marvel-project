package com.acarreno.poc.marvel.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.acarreno.poc.marvel.integration.client.MarvelIntegrationClient;
import com.acarreno.poc.marvel.integration.model.CharacterDataWrapper;
import com.acarreno.poc.marvel.model.Hero;
import com.acarreno.poc.marvel.model.HeroDetail;
import com.acarreno.poc.marvel.model.Item;
import com.acarreno.poc.marvel.model.MarvelResponseType;
import com.acarreno.poc.marvel.model.ServiceType;
import com.acarreno.poc.marvel.persistence.entity.AuditServiceEntity;
import com.acarreno.poc.marvel.persistence.repository.AuditServiceRepository;
import com.acarreno.poc.marvel.service.MarverService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarverServiceImpl implements MarverService {

  private final MarvelIntegrationClient marvelIntegrationClient;
  private final AuditServiceRepository auditServiceRepository;

  @Override
  public List<Hero> getHeroes() {

    MarvelResponseType marvelResponseType = null;
    List<Hero> heroes = Collections.emptyList();

    try {

      CharacterDataWrapper response = marvelIntegrationClient.getCharacters();

      if (response != null && response.getCode() == HttpStatus.OK.value()
          && !response.getData().getResults().isEmpty()) {

        heroes = response.getData().getResults().stream()
            .map(item -> Hero.builder().id(item.getId()).name(item.getName()).build())
            .collect(Collectors.toList());

        marvelResponseType = MarvelResponseType.SUCCESSFULLY;

      } else {
        marvelResponseType = MarvelResponseType.UNSUCCESSFULLY;
      }

      return heroes;

    } catch (Exception e) {
      marvelResponseType = MarvelResponseType.UNSUCCESSFULLY;
    } finally {

      AuditServiceEntity entity =
          AuditServiceEntity.builder().service(ServiceType.GET_CHARACTER.name())
              .codeResponse(marvelResponseType.name()).build();
      auditServiceRepository.save(entity);

    }

    return heroes;
  }

  @Override
  public HeroDetail getHeroeById(int id) {

    MarvelResponseType marvelResponseType = null;
    HeroDetail heroDetail = HeroDetail.builder().build();

    try {

      CharacterDataWrapper response = marvelIntegrationClient.getCharacterById(id);

      if (response != null && response.getCode() == HttpStatus.OK.value()
          && !response.getData().getResults().isEmpty()) {

        marvelResponseType = MarvelResponseType.SUCCESSFULLY;
        com.acarreno.poc.marvel.integration.model.Character character =
            response.getData().getResults().get(0);

        heroDetail = HeroDetail.builder().name(character.getName())
            .description(character.getDescription())
            .imageUrl(
                character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension())
            .comics(character.getComics().getItems().stream()
                .map(item -> Item.builder().name(item.getName()).resourceURI(item.getResourceURI())
                    .build())
                .collect(Collectors.toList()))
            .series(character.getSeries().getItems().stream()
                .map(item -> Item.builder().name(item.getName()).resourceURI(item.getResourceURI())
                    .build())
                .collect(Collectors.toList()))
            .stories(character.getStories().getItems().stream()
                .map(item -> Item.builder().name(item.getName()).resourceURI(item.getResourceURI())
                    .build())
                .collect(Collectors.toList()))
            .events(
                character.getEvents().getItems().stream()
                    .map(item -> Item.builder().name(item.getName())
                        .resourceURI(item.getResourceURI()).build())
                    .collect(Collectors.toList()))
            .build();

      } else {
        marvelResponseType = MarvelResponseType.UNSUCCESSFULLY;
      }

    } catch (Exception e) {
      marvelResponseType = MarvelResponseType.UNSUCCESSFULLY;
    } finally {

      AuditServiceEntity entity =
          AuditServiceEntity.builder().service(ServiceType.GET_CHARACTER_BY_ID.name())
              .codeResponse(marvelResponseType.name()).build();
      auditServiceRepository.save(entity);

    }

    return heroDetail;

  }

}
