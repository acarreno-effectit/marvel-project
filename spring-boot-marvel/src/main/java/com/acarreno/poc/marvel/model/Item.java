package com.acarreno.poc.marvel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Item {

  private String name;
  private String resourceURI;
}
