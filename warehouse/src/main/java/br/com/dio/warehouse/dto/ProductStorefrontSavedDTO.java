package br.com.dio.warehouse.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductStorefrontSavedDTO(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("name")
        String name,
        @JsonProperty("active")
        Boolean active) {

}
