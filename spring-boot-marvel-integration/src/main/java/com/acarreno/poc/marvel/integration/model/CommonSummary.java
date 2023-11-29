package com.acarreno.poc.marvel.integration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class CommonSummary {

  protected String resourceURI;
  protected String name;

}
