package br.com.dio.warehouse.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.dio.warehouse.controller.request.ProductSaveRequest;
import br.com.dio.warehouse.controller.response.ProductDetailResponse;
import br.com.dio.warehouse.controller.response.ProductSavedResponse;
import br.com.dio.warehouse.dto.ProductStorefrontSaveDTO;
import br.com.dio.warehouse.entity.ProductEntity;

@Mapper(componentModel = SPRING)
public interface IProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stocks", ignore = true)
    @Mapping(target = "price", ignore = true)
    ProductEntity toEntity(final ProductSaveRequest request);

    ProductSavedResponse toSavedResponse(final ProductEntity entity);

    ProductStorefrontSaveDTO toDTO(final ProductEntity entity);

    ProductDetailResponse toDetailResponse(final ProductEntity entity);
    
}
