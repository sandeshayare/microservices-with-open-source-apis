package com.eyeray.currencyconversionservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@NoArgsConstructor
@Entity
public class ConversionRate {
    @Id
    private String rateId;

    @JsonIgnore
    private String currencyId;

    private String currencyCode;

    @JsonIgnore
    private String baseCode;

    private Double rate;

    public ConversionRate(String rateId, String currencyId, String currencyCode, String baseCode, Double rate) {
        this.rateId = rateId;
        this.currencyId = currencyId;
        this.currencyCode = currencyCode;
        this.baseCode = baseCode;
        this.rate = rate;
    }

    public static ConversionRate of(String rateId, String currencyId, String baseCode, String currencyCode, Double rate) {
        return new ConversionRate(rateId, currencyId, currencyCode, baseCode, rate);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Double getRate() {
        return rate;
    }

    public String getRateId() {
        return rateId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getBaseCode() {
        return baseCode;
    }
}
