package com.eyeray.currencyconversionservice;

import com.eyeray.currencyconversionservice.model.ConversionRate;
import com.eyeray.currencyconversionservice.model.ConversionRates;
import com.eyeray.currencyconversionservice.model.Currency;
import com.eyeray.currencyconversionservice.model.Root;
import com.eyeray.currencyconversionservice.repository.ConversionRateRepository;
import com.eyeray.currencyconversionservice.repository.CurrencyConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

import static com.eyeray.currencyconversionservice.constants.CurrencyConstants.*;

@EnableEurekaClient
@SpringBootApplication
public class CurrencyConversionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionServiceApplication.class, args);
    }

    @Value("${exchange-rate-api.url}")
    private String URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConversionRateRepository conversionRateRepository;

    @Autowired
    private CurrencyConversionRepository currencyConversionRepository;

    @Bean
    ApplicationRunner loadInitialData() {
        List<String> currencies = Arrays.asList(INR, USD, EUR, GBP, JPY);
        return args -> currencies.forEach(this::callExchangeRateAPI);
    }

    private void callExchangeRateAPI(String currency) {
        ResponseEntity<Root> responseEntity = restTemplate.exchange(URI.create(URL.concat(currency)), HttpMethod.GET, getHttpEntity(), Root.class);
        Root root = responseEntity.getBody();
        String currencyId = String.valueOf(String.valueOf(UUID.randomUUID()));
        currencyConversionRepository.save(Currency.of(currencyId, Objects.requireNonNull(root).base_code));
        getConversionRates(root, currencyId).forEach(conversionRate -> conversionRateRepository.save(conversionRate));
    }

    private HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

    private List<ConversionRate> getConversionRates(Root root, String currencyId) {
        List<ConversionRate> conversionRateList = new ArrayList<>();
        final ConversionRates rate = root.conversion_rates;
        final String base_code = root.base_code;
        conversionRateList.add(ConversionRate.of(String.valueOf(UUID.randomUUID()), currencyId, base_code, INR, rate.INR));
        conversionRateList.add(ConversionRate.of(String.valueOf(UUID.randomUUID()), currencyId, base_code, USD, rate.USD));
        conversionRateList.add(ConversionRate.of(String.valueOf(UUID.randomUUID()), currencyId, base_code, EUR, rate.EUR));
        conversionRateList.add(ConversionRate.of(String.valueOf(UUID.randomUUID()), currencyId, base_code, GBP, rate.GBP));
        conversionRateList.add(ConversionRate.of(String.valueOf(UUID.randomUUID()), currencyId, base_code, JPY, rate.JPY));
        conversionRateList.removeIf(conversionRate -> Objects.equals(base_code, conversionRate.getCurrencyCode()));
        return conversionRateList;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}


