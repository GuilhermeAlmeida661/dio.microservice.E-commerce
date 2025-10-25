package br.com.dio.storefront.dto;

import java.math.BigDecimal;
import java.util.UUID;

// Data Transfer Object for product information
public record ProductInfoDTO(UUID id, String name, BigDecimal price) {
} 