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
public class Character {

  private int id;
  private String name;
  private String description;
  private String modified;
  private String resourceURI;
  private List<Url> urls;
  private Image thumbnail;
  private ComicList comics;
  private StoryList stories;
  private EventList events;
  private SeriesList series;

}
