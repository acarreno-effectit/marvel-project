package com.acarreno.poc.marvel.integration.service;

import com.acarreno.poc.marvel.integration.model.CharacterDataWrapper;

public interface MarvelIntegration {

  CharacterDataWrapper getCharacters();

  CharacterDataWrapper getCharacterById(int id);

}
