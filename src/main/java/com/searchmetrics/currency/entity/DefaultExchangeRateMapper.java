package com.searchmetrics.currency.entity;

import com.searchmetrics.currency.data.ExchangeRateData;
import org.springframework.stereotype.Component;

/**
 * @author Slavko Lozić (slozic1@gmail.com)
 */
@Component
public class DefaultExchangeRateMapper
{
    private static final String CURRENCY_USD = "USD";

    public ExchangeRate mapExchangeRate(ExchangeRateData latestRate)
    {
        ExchangeRate rate = new ExchangeRate();
        rate.setTimestamp(System.currentTimeMillis());
        rate.setDate(latestRate.getDate());
        rate.setBase(latestRate.getBase());
        rate.setCurrency(CURRENCY_USD);
        rate.setRate(latestRate.getRates().get(CURRENCY_USD));
        return rate;
    }
}
