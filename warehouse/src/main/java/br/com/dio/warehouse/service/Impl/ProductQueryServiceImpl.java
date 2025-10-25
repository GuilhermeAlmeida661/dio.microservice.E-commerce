package br.com.dio.warehouse.service.Impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.dio.warehouse.repository.ProductRepository;
import br.com.dio.warehouse.entity.ProductEntity;
import br.com.dio.warehouse.service.IProductQueryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductQueryServiceImpl implements IProductQueryService{

    private final ProductRepository repository;    

    @Override
    public ProductEntity findById(final UUID id) {
        return repository.findById(id).orElseThrow();
    }

}
