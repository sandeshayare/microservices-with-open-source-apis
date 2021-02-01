package com.eyeray.currencyconversionservice.repository;

import com.eyeray.currencyconversionservice.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CurrencyConversionRepository extends JpaRepository<Currency, String> {
}
