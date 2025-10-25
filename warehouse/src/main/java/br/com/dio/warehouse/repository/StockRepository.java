package br.com.dio.warehouse.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dio.warehouse.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, UUID> {

}
