package com.acarreno.poc.marvel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Hero {

  private int id;

  private String name;

}
