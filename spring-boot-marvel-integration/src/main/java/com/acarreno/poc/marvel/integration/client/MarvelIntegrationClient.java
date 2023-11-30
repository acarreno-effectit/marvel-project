package com.acarreno.poc.marvel.integration.client;

import com.acarreno.poc.marvel.integration.model.CharacterDataWrapper;

public interface MarvelIntegrationClient {

  CharacterDataWrapper getCharacters();

  CharacterDataWrapper getCharacterById(int id);

}
