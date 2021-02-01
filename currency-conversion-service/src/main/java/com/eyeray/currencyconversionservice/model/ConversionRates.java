package com.eyeray.currencyconversionservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversionRates {
    @JsonProperty("INR")
    public double INR;
    @JsonProperty("USD")
    public double USD;
    @JsonProperty("EUR")
    public double EUR;
    @JsonProperty("GBP")
    public double GBP;
    @JsonProperty("JPY")
    public double JPY;
}
