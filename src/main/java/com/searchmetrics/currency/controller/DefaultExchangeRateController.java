package com.searchmetrics.currency.controller;

import com.searchmetrics.currency.entity.ExchangeRate;
import com.searchmetrics.currency.service.ExchangeRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Slavko Lozić (slozic1@gmail.com)
 */
@RestController
public class DefaultExchangeRateController
{
    private final ExchangeRateService exchangeRateService;

    public DefaultExchangeRateController(
            ExchangeRateService exchangeRateService)
    {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/latest")
    public ExchangeRate getLatestRate()
    {
        return exchangeRateService.getLatestRate();
    }

    @GetMapping("/timeseries")
    public List<ExchangeRate> getRatesBetweenDates(
            @RequestParam(value = "startDate")
                    String startDate,
            @RequestParam(value = "endDate")
                    String endDate
    )
    {
        return exchangeRateService.getHistoricalRates(startDate, endDate);
    }
}
