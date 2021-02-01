package com.eyeray.currencyconversionservice.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class Currency {
    @Id
    @Column(name = "currency_id")
    private String currencyId;
    private String baseCode;

    public Currency(String currencyId, String baseCode) {
        this.currencyId = currencyId;
        this.baseCode = baseCode;
    }

    public static Currency of(String currencyId, String baseCode) {
        return new Currency(currencyId, baseCode);
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getCurrencyId() {
        return currencyId;
    }
}
