package br.com.dio.warehouse.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringExclude;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

import static jakarta.persistence.EnumType.STRING;
import static br.com.dio.warehouse.entity.StockStatus.UNAVAILABLE;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class StockEntity {

    @Id
    private UUID id;

    private Long amount;

    //Preço de compra
    private BigDecimal boughtPrice;

    @Enumerated(STRING) 
    private StockStatus status;

    //Preço de venda
    private BigDecimal soldPrice;

    //método para decrementar do estoque
    public void decAmount(){
        this.amount -= 1;
        if(this.amount == 0){
            this.status = UNAVAILABLE;
        }
    }

    public Boolean isUnavailable(){
        return status == UNAVAILABLE;
    }

    @ToStringExclude
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Override
    public int hashCode() {
          return Objects.hash(id, amount, boughtPrice, status, soldPrice);
    }

    @Override
    public boolean equals(final Object o) {
       if(!(o instanceof StockEntity that)) return false;
        return Objects.equals(id, that.id) &&
               Objects.equals(amount, that.amount) &&
               Objects.equals(boughtPrice, that.boughtPrice) &&
               status== that.status &&
               Objects.equals(soldPrice, that.soldPrice);
    }

    //Resolvendo o problema do h2 com o UUID na criação da tabela
    @PrePersist
    private void prePersist(){
        this.id = UUID.randomUUID();
    }
}
