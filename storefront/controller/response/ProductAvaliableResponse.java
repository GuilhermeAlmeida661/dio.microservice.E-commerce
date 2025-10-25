package br.com.dio.storefront.controller.response;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductAvaliableResponse(
    @JsonProperty("id")
    UUID id,
    @JsonProperty("name")
    String name
) {

}
