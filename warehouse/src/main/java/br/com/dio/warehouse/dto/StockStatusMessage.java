package br.com.dio.warehouse.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.dio.warehouse.entity.StockStatus;

public record StockStatusMessage(
    @JsonProperty("id")
    UUID id,
    @JsonProperty("status")
    StockStatus status){
        
    }
