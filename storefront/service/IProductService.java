package br.com.dio.storefront.service;

import java.util.List;
import java.util.UUID;

import br.com.dio.storefront.dto.ProductInfoDTO;
import br.com.dio.storefront.entity.ProductEntity;

// Service interface for product-related operations
public interface IProductService {
    
    ProductEntity save(final ProductEntity entity);

    void changeActivated(final UUID id, final boolean active);

    List<ProductEntity> findAllActive();

    ProductInfoDTO findInfo(final UUID id);

    void purchase(final UUID id);
}
