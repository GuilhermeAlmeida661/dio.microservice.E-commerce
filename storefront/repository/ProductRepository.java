package br.com.dio.storefront.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dio.storefront.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    // Custom query method to find all active products ordered by name ascending
    List<ProductEntity> findByActiveTrueOrderByNameAsc();
}

