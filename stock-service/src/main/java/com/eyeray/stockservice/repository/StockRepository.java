package com.eyeray.stockservice.repository;

import com.eyeray.stockservice.model.StockWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "stocks", collectionResourceRel = "stocks")
public interface StockRepository extends JpaRepository<StockWrapper, String> {
    @RestResource(path = "by-symbol", rel = "by-symbol")
    StockWrapper findBySymbol(@Param("symbol") String symbol);
}
