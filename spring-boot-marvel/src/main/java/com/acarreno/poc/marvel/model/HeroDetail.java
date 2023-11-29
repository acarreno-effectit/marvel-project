package com.acarreno.poc.marvel.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HeroDetail {

  private String name;
  private String description;
  private String imageUrl;
  private List<Item> comics;
  private List<Item> series;
  private List<Item> stories;
  private List<Item> events;

}
