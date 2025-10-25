package br.com.dio.storefront.dto;

import java.util.UUID;

// Data Transfer Object for stock status messages
public record StockStatusMessage(UUID id, String status) {

    public boolean active(){
        return status.equals("AVAILABLE");
    }
}
