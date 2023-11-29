package com.acarreno.poc.marvel.integration.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDataContainer {

  private int offset;
  private int limit;
  private int total;
  private int count;
  private List<Character> results;

}
