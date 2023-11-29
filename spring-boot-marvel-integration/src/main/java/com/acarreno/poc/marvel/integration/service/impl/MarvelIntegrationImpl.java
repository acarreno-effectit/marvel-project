package com.acarreno.poc.marvel.integration.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import com.acarreno.poc.marvel.integration.model.CharacterDataWrapper;
import com.acarreno.poc.marvel.integration.service.MarvelIntegration;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarvelIntegrationImpl implements MarvelIntegration {

  private WebClient webClient;
  private final Builder webClientBuilder;

  @Value("${marvel.api.endpoint.base-url}")
  private String baseUrl;

  @Value("${marvel.api.public-key}")
  private String publicKey;

  @Value("${marvel.api.private-key}")
  private String privateKey;

  @Value("${marvel.api.endpoint.characters}")
  private String endpointCharacters;

  @Override
  public CharacterDataWrapper getCharacters() {

    webClient = webClientBuilder.baseUrl(baseUrl).build();

    long timestamp = System.currentTimeMillis();

    CharacterDataWrapper responseBody = webClient.get()
        .uri(uriBuilder -> uriBuilder.path(endpointCharacters)
            .queryParam("apikey", publicKey)
            .queryParam("hash", getMD5Hash(timestamp))
            .queryParam("ts", timestamp)
            .queryParam("limit", 50)
            .build())
        .retrieve().bodyToMono(CharacterDataWrapper.class).block();

    return responseBody;
  }

  @Override
  public CharacterDataWrapper getCharacterById(int id) {
    
    webClient = webClientBuilder.baseUrl(baseUrl).build();

    long timestamp = System.currentTimeMillis();

    CharacterDataWrapper responseBody = webClient.get()
        .uri(uriBuilder -> uriBuilder.path(endpointCharacters + "/" + id)
            .queryParam("apikey", publicKey)
            .queryParam("hash", getMD5Hash(timestamp))
            .queryParam("ts", timestamp).build())
        .retrieve().bodyToMono(CharacterDataWrapper.class).block();

    return responseBody;
  }


  private String getMD5Hash(long timestamp) {

    try {
      StringBuilder builder = new StringBuilder();
      builder.append(timestamp).append(privateKey).append(publicKey);

      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(builder.toString().getBytes());

      StringBuilder hexString = new StringBuilder();

      for (byte b : messageDigest) {
        String hex = Integer.toHexString(0xFF & b);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }

      return hexString.toString();

    } catch (NoSuchAlgorithmException e) {
      return null;
    }

  }

}
