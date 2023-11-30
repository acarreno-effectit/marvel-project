package com.acarreno.poc.marvel.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.acarreno.poc.marvel.integration.client.MarvelIntegrationClient;
import com.acarreno.poc.marvel.integration.model.CharacterDataWrapper;
import com.acarreno.poc.marvel.model.Hero;
import com.acarreno.poc.marvel.model.HeroDetail;
import com.acarreno.poc.marvel.persistence.entity.AuditServiceEntity;
import com.acarreno.poc.marvel.persistence.repository.AuditServiceRepository;
import com.acarreno.poc.marvel.service.impl.MarverServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class MarverServiceTest {

  @Mock
  private AuditServiceRepository auditServiceRepository;

  @Mock
  private MarvelIntegrationClient marvelIntegration;

  @InjectMocks
  private MarverServiceImpl service;

  @Test
  void getHeroesSuccessful() {

    AuditServiceEntity entity = AuditServiceEntity.builder().id(UUID.randomUUID()).build();

    when(auditServiceRepository.save(Mockito.any(AuditServiceEntity.class))).thenReturn(entity);
    when(marvelIntegration.getCharacters()).thenReturn(buildMockCharacterDataWrapper());

    List<Hero> response = service.getHeroes();
    assertTrue(!response.isEmpty());
    assertTrue(response.size() == 2);

  }

  @Test
  void getHeroesErrorToConnectMarvelService() {

    AuditServiceEntity entity = AuditServiceEntity.builder().id(UUID.randomUUID()).build();

    when(auditServiceRepository.save(Mockito.any(AuditServiceEntity.class))).thenReturn(entity);

    List<Hero> response = service.getHeroes();
    assertTrue(response.isEmpty());

  }

  @Test
  void getHeroByIdErrorToConnectMarvelService() {

    AuditServiceEntity entity = AuditServiceEntity.builder().id(UUID.randomUUID()).build();

    when(auditServiceRepository.save(Mockito.any(AuditServiceEntity.class))).thenReturn(entity);

    HeroDetail response = service.getHeroeById(0);
    assertTrue(response.getName() == null);

  }

  @Test
  void getHeroByIdSuccessful() {

    AuditServiceEntity entity = AuditServiceEntity.builder().id(UUID.randomUUID()).build();

    when(auditServiceRepository.save(Mockito.any(AuditServiceEntity.class))).thenReturn(entity);
    when(marvelIntegration.getCharacterById(0)).thenReturn(buildMockCharacterDataWrapper());

    HeroDetail response = service.getHeroeById(0);
    assertTrue(response != null);
    assertTrue(response.getName() != null);

  }

  private static CharacterDataWrapper buildMockCharacterDataWrapper() {
    try {

      File file = new File("src/main/resources/data.json");
      ObjectMapper objectMapper = new ObjectMapper();
      CharacterDataWrapper result = objectMapper.readValue(file, CharacterDataWrapper.class);

      return result;

    } catch (IOException e) {
      return null;
    }

  }

}
