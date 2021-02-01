package com.eyeray.currencyconversionservice.repository;

import com.eyeray.currencyconversionservice.model.ConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;

@RepositoryRestResource(path = "currency-converter")
public interface ConversionRateRepository extends JpaRepository<ConversionRate, String> {
    @RestResource(path = "by-currency", rel = "by-currency")
    Collection<ConversionRate> getConversionRateByBaseCode(@Param("currency") String currency);
}
