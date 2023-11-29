package com.acarreno.poc.marvel.service;

import java.util.List;
import com.acarreno.poc.marvel.model.Hero;
import com.acarreno.poc.marvel.model.HeroDetail;

public interface MarverService {

  List<Hero> getHeroes();
  
  HeroDetail getHeroeById(int id);
}
