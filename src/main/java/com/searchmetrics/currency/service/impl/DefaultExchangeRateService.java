package com.searchmetrics.currency.service.impl;

import com.searchmetrics.currency.data.ExchangeRateData;
import com.searchmetrics.currency.entity.ExchangeRate;
import com.searchmetrics.currency.repository.ExchangeRateRepository;
import com.searchmetrics.currency.service.ExchangeRateService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author Slavko Lozić (slozic1@gmail.com)
 */
@Service
public class DefaultExchangeRateService implements ExchangeRateService
{
    private static final String CURRENCY_USD = "USD";
    private static final String EXCHANGE_RATE_API = "http://data.fixer.io/api/latest";

    private final ExchangeRateRepository exchangeRateRepository;
    private final Environment environment;

    public DefaultExchangeRateService(
            ExchangeRateRepository exchangeRateRepository,
            Environment environment
    )
    {
        this.exchangeRateRepository = exchangeRateRepository;
        this.environment = environment;
    }

    @Override
    public ExchangeRateData getLatestRateFromPublicApi()
    {
        String uri = UriComponentsBuilder.fromUriString(EXCHANGE_RATE_API)
                                         .queryParam("access_key", getApiKey())
                                         .queryParam("symbols", CURRENCY_USD)
                                         .build()
                                         .toString();
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(uri, ExchangeRateData.class);
    }

    @Override
    public ExchangeRate getLatestRate()
    {
        return exchangeRateRepository.findFirstByOrderByTimestampDesc();
    }

    @Override
    public List<ExchangeRate> getHistoricalRates(
            String startDate,
            String endDate
    )
    {
        return exchangeRateRepository.findAllByDateBetween(startDate, endDate);
    }

    private String getApiKey()
    {
        return environment.getProperty("api.key");
    }
}
