package com.eyeray.stockservice;

import com.eyeray.stockservice.model.StockWrapper;
import com.eyeray.stockservice.repository.StockRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static com.eyeray.stockservice.constants.StockConstants.*;

@EnableEurekaClient
@SpringBootApplication
public class StockServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner loadInitialData(StockRepository stockRepository) throws IOException {
        return args -> {
            String[] symbols = new String[]{INTC, BABA, TSLA, AIR_PA};
            Map<String, Stock> stocks = YahooFinance.get(symbols, true);
            Stream.of(symbols).forEach(symbol -> stockRepository.save(buildStockWrapper(stocks, symbol)));
        };
    }

    private StockWrapper buildStockWrapper(Map<String, Stock> stocksMap, String symbol) {
        Stock stock = stocksMap.get(symbol);
        return StockWrapper.of(String.valueOf(UUID.randomUUID()), symbol, stock.getName(), stock.getQuote().getChange(), stock.getQuote().getPrice());
    }
}

