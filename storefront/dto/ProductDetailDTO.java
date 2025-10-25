package br.com.dio.storefront.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

// Data Transfer Object for detailed product information
public record ProductDetailDTO( 
    @JsonProperty("id")
    UUID id,
    @JsonProperty("name")
    String name,
    @JsonProperty("price")
    BigDecimal price) {

    }

