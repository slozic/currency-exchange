package com.searchmetrics.currency.service;

import com.searchmetrics.currency.data.ExchangeRateData;
import com.searchmetrics.currency.entity.ExchangeRate;

import java.util.List;

/**
 * @author Slavko Lozić (slozic1@gmail.com)
 */
public interface ExchangeRateService
{
    /**
     * @return ExchangeRateData
     */
    ExchangeRateData getLatestRateFromPublicApi();

    /**
     * @return ExchangeRate
     */
    ExchangeRate getLatestRate();

    /**
     * @param startDate
     * @param endDate
     * @return List<ExchangeRate>
     */
    List<ExchangeRate> getHistoricalRates(String startDate, String endDate);
}
