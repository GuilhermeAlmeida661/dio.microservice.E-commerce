package br.com.dio.warehouse.service;

import java.util.UUID;

import br.com.dio.warehouse.entity.ProductEntity;

public interface IProductQueryService {

    ProductEntity findById(final UUID id);

}
