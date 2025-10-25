package br.com.dio.warehouse.mapper;

import br.com.dio.warehouse.controller.request.StockSaveRequest;
import br.com.dio.warehouse.controller.response.StockSavedResponse;
import br.com.dio.warehouse.entity.ProductEntity;
import br.com.dio.warehouse.entity.StockEntity;
import br.com.dio.warehouse.entity.StockStatus;
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
public class IStockMapperImpl implements IStockMapper {

    @Override
    public StockEntity toEntity(StockSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        StockEntity stockEntity = new StockEntity();

        stockEntity.setProduct( stockSaveRequestToProductEntity( request ) );
        stockEntity.setAmount( request.amount() );
        stockEntity.setBoughtPrice( request.boughtPrice() );
        stockEntity.setSoldPrice( request.soldPrice() );

        stockEntity.setStatus( br.com.dio.warehouse.entity.StockStatus.IN_CONFERENCE );

        return stockEntity;
    }

    @Override
    public StockSavedResponse toResponse(StockEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID productId = null;
        String productName = null;
        BigDecimal boughtPrice = null;
        UUID id = null;
        Long amount = null;
        StockStatus status = null;
        BigDecimal soldPrice = null;

        productId = entityProductId( entity );
        productName = entityProductName( entity );
        boughtPrice = entity.getBoughtPrice();
        id = entity.getId();
        amount = entity.getAmount();
        status = entity.getStatus();
        soldPrice = entity.getSoldPrice();

        StockSavedResponse stockSavedResponse = new StockSavedResponse( id, amount, boughtPrice, status, soldPrice, productId, productName );

        return stockSavedResponse;
    }

    protected ProductEntity stockSaveRequestToProductEntity(StockSaveRequest stockSaveRequest) {
        if ( stockSaveRequest == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( stockSaveRequest.productId() );

        return productEntity;
    }

    private UUID entityProductId(StockEntity stockEntity) {
        ProductEntity product = stockEntity.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private String entityProductName(StockEntity stockEntity) {
        ProductEntity product = stockEntity.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }
}
