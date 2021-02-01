package com.eyeray.stockservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class StockWrapper {
    @Id
    @JsonIgnore
    private String stockCode;
    private String symbol;
    private String name;
    private BigDecimal change;
    private BigDecimal price;

    private StockWrapper(String stockCode, String symbol, String name, BigDecimal change, BigDecimal price) {
        this.stockCode = stockCode;
        this.symbol = symbol;
        this.name = name;
        this.change = change;
        this.price = price;
    }

    public static StockWrapper of(String stockCode, String symbol, String name, BigDecimal change, BigDecimal price) {
        return new StockWrapper(stockCode, symbol, name, change, price);
    }
}
