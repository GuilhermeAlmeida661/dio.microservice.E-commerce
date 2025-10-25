package br.com.dio.storefront.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.dio.storefront.dto.ProductDetailDTO;
import br.com.dio.storefront.dto.ProductInfoDTO;
import br.com.dio.storefront.entity.ProductEntity;
import br.com.dio.storefront.mapper.IProductMapper;
import br.com.dio.storefront.repository.ProductRepository;
import br.com.dio.storefront.service.IProductService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final RestClient warehouseClient;
    private final IProductMapper mapper;

    //Salva o produto na Lista
    @Override
    public ProductEntity save(ProductEntity entity) {
        return repository.save(entity);
    }

    //Muda o status do produto para ativo ou inativo
    @Override
    public void changeActivated(UUID id, boolean active) {
        var entity = findById(id);
        entity.setActive(active);
        repository.save(entity);  
    }

    //Busca todos os produtos ativos
    @Override
    public List<ProductEntity> findAllActive() {
        return repository.findByActiveTrueOrderByNameAsc();
    }

    // Encontra as informações do produto pelo ID
    @Override
    public ProductInfoDTO findInfo(UUID id) {
        var entity = findById(id);
        var price = requestCurrentAmount(id);
        return mapper.toDTO(entity, price);
    }

    // Realiza a compra do produto pelo ID
    @Override
    public void purchase(UUID id) {
        purchaseWarehouse(id);
    }

    // Método auxiliar para encontrar o produto pelo ID
    private ProductEntity findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

    }

    // Método auxiliar para solicitar o valor atual do produto ao serviço de estoque
    private BigDecimal requestCurrentAmount(final UUID id) {
        var dto = warehouseClient.get()
                .uri("/products/" + id)
                .retrieve()
                .body(ProductDetailDTO.class);
        return dto.price();
    }

    // Método auxiliar para notificar o serviço de estoque sobre a compra do produto
    private void purchaseWarehouse(final UUID id){
        var path = String.format("/products/%s/purchase", id);
        warehouseClient.post()
            .uri(path)
            .retrieve()
            .toBodilessEntity();
    }
}
