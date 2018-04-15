package com.searchmetrics.currency.scheduler;

import com.searchmetrics.currency.data.ExchangeRateData;
import com.searchmetrics.currency.entity.DefaultExchangeRateMapper;
import com.searchmetrics.currency.entity.ExchangeRate;
import com.searchmetrics.currency.repository.ExchangeRateRepository;
import com.searchmetrics.currency.service.ExchangeRateService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Slavko Lozić (slozi1@gmail.com)
 */
@Component
public class EchangeRateScheduler
{
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateService exchangeRateService;
    private final DefaultExchangeRateMapper defaultExchangeRateMapper;

    public EchangeRateScheduler(
            ExchangeRateRepository exchangeRateRepository,
            ExchangeRateService exchangeRateService,
            DefaultExchangeRateMapper defaultExchangeRateMapper
    )
    {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateService = exchangeRateService;
        this.defaultExchangeRateMapper = defaultExchangeRateMapper;
    }

    /**
     * Triggers call in configurable interval to get exchange rates from public api.
     * Calls data mapper to get entity.
     * Then calls repository to do save.
     */
    @Scheduled(fixedRateString = "${exchangerate.interval}")
    public void getCurrencyExchangeRate()
    {
        ExchangeRateData latestRate = exchangeRateService.getLatestRateFromPublicApi();
        ExchangeRate exchangeRate = defaultExchangeRateMapper.mapExchangeRate(latestRate);
        exchangeRateRepository.save(exchangeRate);
    }
}
