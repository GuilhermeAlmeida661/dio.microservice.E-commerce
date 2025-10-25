package br.com.dio.warehouse.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.dio.warehouse.controller.request.StockSaveRequest;
import br.com.dio.warehouse.controller.response.StockSavedResponse;
import br.com.dio.warehouse.entity.StockEntity;

@Mapper(componentModel = SPRING)
public interface IStockMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "status", expression = "java(br.com.dio.warehouse.entity.StockStatus.IN_CONFERENCE)") 
    StockEntity toEntity(final StockSaveRequest request);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "boughtPrice", source = "boughtPrice")
    StockSavedResponse toResponse(final StockEntity entity);
}
