package br.com.dio.warehouse.entity;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringExclude;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import static jakarta.persistence.CascadeType.ALL;

import static br.com.dio.warehouse.entity.StockStatus.AVAILABLE;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class ProductEntity {
    
    @Id
    private UUID id;

    private String name;

    //Relacionamento com o estoque - bidirecional
    @ToStringExclude
    @OneToMany(mappedBy = "product", cascade = ALL, orphanRemoval = true)
    private Set<StockEntity> stocks = new HashSet<>();

    //método para pegar o menor valor de venda
    private StockEntity getStockWithMinSoldPrice(){
        return this.stocks.stream()
                .filter(s-> s.getStatus().equals(AVAILABLE))
                .min(Comparator.comparing(StockEntity::getSoldPrice))
                .orElseThrow();
    }

    //método para decrementar o estoque e se basear pelo menor valor de venda
    public StockEntity decStock(){
        var stock = getStockWithMinSoldPrice();
        stock.decAmount();
        return stock;
    }

    public BigDecimal getPrice(){
        return getStockWithMinSoldPrice().getSoldPrice();
    }

    @Override
    public int hashCode() {
          return Objects.hash(id, name);
    }

    @Override
    public boolean equals(final Object o) {
       if(!(o instanceof ProductEntity that)) return false;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name);
    }
   
    //Resolvendo o problema do h2 com o UUID na criação da tabela
    @PrePersist
    private void prePersist(){
        this.id = UUID.randomUUID();
    }
}
