package com.acarreno.poc.marvel.integration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDataWrapper {

  private int code;
  private String status;
  private String copyright;
  private String attributionText;
  private String attributionHTML;
  private CharacterDataContainer data;
  private String etag;

}
