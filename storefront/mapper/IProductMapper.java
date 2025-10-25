package br.com.dio.storefront.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.dio.storefront.controller.request.ProductSaveRequest;
import br.com.dio.storefront.controller.response.ProductAvaliableResponse;
import br.com.dio.storefront.controller.response.ProductDetailResponse;
import br.com.dio.storefront.controller.response.ProductSavedResponse;
import br.com.dio.storefront.dto.ProductInfoDTO;
import br.com.dio.storefront.entity.ProductEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.math.BigDecimal;
import java.util.List;

// Mapper interface for "converting" ProductEntity to ProductInfoDTO
@Mapper(componentModel = SPRING)
public interface IProductMapper {

    ProductInfoDTO toDTO(final ProductEntity entity, final BigDecimal price);

    @Mapping(target = "active", constant = "false") 
    ProductEntity toEntity(final ProductSaveRequest request);

    ProductSavedResponse toResponseSaved(final ProductEntity entity);

    List<ProductAvaliableResponse> toResponseAvaliable(final List<ProductEntity> entities);

    ProductDetailResponse toResponseDetail(final ProductInfoDTO dto);

}
