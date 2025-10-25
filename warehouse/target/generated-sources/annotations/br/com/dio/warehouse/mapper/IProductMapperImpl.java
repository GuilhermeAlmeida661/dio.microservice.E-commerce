package br.com.dio.warehouse.mapper;

import br.com.dio.warehouse.controller.request.ProductSaveRequest;
import br.com.dio.warehouse.controller.response.ProductDetailResponse;
import br.com.dio.warehouse.controller.response.ProductSavedResponse;
import br.com.dio.warehouse.dto.ProductStorefrontSaveDTO;
import br.com.dio.warehouse.entity.ProductEntity;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-24T16:51:38-0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class IProductMapperImpl implements IProductMapper {

    @Override
    public ProductEntity toEntity(ProductSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName( request.name() );

        return productEntity;
    }

    @Override
    public ProductSavedResponse toSavedResponse(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = entity.getId();
        name = entity.getName();

        ProductSavedResponse productSavedResponse = new ProductSavedResponse( id, name );

        return productSavedResponse;
    }

    @Override
    public ProductStorefrontSaveDTO toDTO(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = entity.getId();
        name = entity.getName();

        ProductStorefrontSaveDTO productStorefrontSaveDTO = new ProductStorefrontSaveDTO( id, name );

        return productStorefrontSaveDTO;
    }

    @Override
    public ProductDetailResponse toDetailResponse(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        BigDecimal price = null;

        id = entity.getId();
        name = entity.getName();
        price = entity.getPrice();

        ProductDetailResponse productDetailResponse = new ProductDetailResponse( id, name, price );

        return productDetailResponse;
    }
}
