package br.com.dio.warehouse.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductStorefrontSaveDTO(
    @JsonProperty("id")
    UUID id,
    @JsonProperty("name")
    String name
) {

}
