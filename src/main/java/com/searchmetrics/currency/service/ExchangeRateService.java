package com.searchmetrics.currency.service;

import com.searchmetrics.currency.data.ExchangeRateData;
import com.searchmetrics.currency.entity.ExchangeRate;

import java.util.List;

/**
 * @author Slavko Lozić (slozic1@gmail.com)
 */
public interface ExchangeRateService
{
    ExchangeRateData getLatestRateFromPublicApi();

    ExchangeRate getLatestRate();

    List<ExchangeRate> getHistoricalRates(String startDate, String endDate);
}
