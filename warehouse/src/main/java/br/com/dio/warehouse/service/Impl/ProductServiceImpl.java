package br.com.dio.warehouse.service.Impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.dio.warehouse.dto.ProductStorefrontSaveDTO;
import br.com.dio.warehouse.dto.ProductStorefrontSavedDTO;
import br.com.dio.warehouse.repository.ProductRepository;
import br.com.dio.warehouse.entity.ProductEntity;
import br.com.dio.warehouse.mapper.IProductMapper;
import br.com.dio.warehouse.service.IProductQueryService;
import br.com.dio.warehouse.service.IProductService;
import br.com.dio.warehouse.service.IStockService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final ProductRepository repository;
    private final IProductQueryService queryService;
    private final IStockService stockService;
    private final RestClient storefrontClient;
    private final IProductMapper mapper;
    

    @Override
    public ProductEntity save(final ProductEntity entity) {
        repository.save(entity);
        var dto = mapper.toDTO(entity);
        saveStorefront(dto);
        return entity;
    }

    @Override
    public void purchase(final UUID id) {
        var entity = queryService.findById(id);
        var stock = entity.decStock();
        repository.save(entity);
        if(stock.isUnavailable()){
            stockService.changeStatus(entity.getId(), stock.getStatus());
        }
    }

    private void saveStorefront(final ProductStorefrontSaveDTO dto) {
        storefrontClient.post()
                        .uri("/products")
                        .body(dto)
                        .retrieve()
                        .body(ProductStorefrontSavedDTO.class);
    }
}
