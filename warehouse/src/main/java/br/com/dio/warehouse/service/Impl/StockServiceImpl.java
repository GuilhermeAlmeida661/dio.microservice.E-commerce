package br.com.dio.warehouse.service.Impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import static br.com.dio.warehouse.entity.StockStatus.AVAILABLE;
import static br.com.dio.warehouse.entity.StockStatus.UNAVAILABLE;
import br.com.dio.warehouse.dto.StockStatusMessage;
import br.com.dio.warehouse.entity.StockEntity;
import br.com.dio.warehouse.entity.StockStatus;
import br.com.dio.warehouse.repository.StockRepository;
import br.com.dio.warehouse.service.IProductChangeAvailabilityProducer;
import br.com.dio.warehouse.service.IProductQueryService;
import br.com.dio.warehouse.service.IStockService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {
    
    private final StockRepository repository;
    private final IProductQueryService productQueryService;
    private final IProductChangeAvailabilityProducer producer;

    //Recebe o produto e seu id e salva o produto
    @Override
    public StockEntity save(StockEntity entity) {
        entity.setProduct(productQueryService.findById(entity.getProduct().getId()));
        return repository.save(entity);
    }

    //libera o produto para compra
    @Override
    public void release(UUID id) {
        changeStatus(id, AVAILABLE);
    }

    //indisponibiliza o produto para compra
    @Override
    public void inactive(UUID id) {
        changeStatus(id, UNAVAILABLE);
    }

    //mensagem para o rabbitmq de mudança de status
    @Override
    public void changeStatus(UUID id, StockStatus status) {
        var entity = repository.findById(id).orElseThrow();
        entity.setStatus(status);
        repository.save(entity);
        producer.notifyStatusChange(new StockStatusMessage(entity.getProduct().getId(), status));
    }


}
