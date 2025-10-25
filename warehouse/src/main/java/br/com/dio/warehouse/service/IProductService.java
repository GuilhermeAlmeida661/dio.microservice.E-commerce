package br.com.dio.warehouse.service;

import java.util.UUID;

import br.com.dio.warehouse.entity.ProductEntity;

public interface IProductService {

    ProductEntity save(final ProductEntity entity);

    void purchase(final UUID id);
}
