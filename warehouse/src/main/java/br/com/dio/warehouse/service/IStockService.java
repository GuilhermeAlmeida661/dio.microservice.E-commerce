package br.com.dio.warehouse.service;

import java.util.UUID;

import br.com.dio.warehouse.entity.StockEntity;
import br.com.dio.warehouse.entity.StockStatus;

public interface IStockService {

    StockEntity save(final StockEntity entity);

    void release(final UUID id);

    void inactive(final UUID id);

    void changeStatus(final UUID id, final StockStatus status);
}
