package com.searchmetrics.currency.scheduler;

import com.searchmetrics.currency.data.ExchangeRateData;
import com.searchmetrics.currency.entity.DefaultExchangeRateMapper;
import com.searchmetrics.currency.entity.ExchangeRate;
import com.searchmetrics.currency.repository.ExchangeRateRepository;
import com.searchmetrics.currency.service.ExchangeRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author Slavko Lozić
 */
@Component
public class EchangeRateScheduler
{
    private static final Logger log = LoggerFactory.getLogger(EchangeRateScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final long checkInterval = 10000;

    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateService exchangeRateService;
    private final DefaultExchangeRateMapper defaultExchangeRateMapper;
    private final Environment environment;

    public EchangeRateScheduler(
            ExchangeRateRepository exchangeRateRepository,
            ExchangeRateService exchangeRateService,
            DefaultExchangeRateMapper defaultExchangeRateMapper,
            Environment environment
    )
    {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateService = exchangeRateService;
        this.defaultExchangeRateMapper = defaultExchangeRateMapper;
        this.environment = environment;
    }

    @Scheduled(fixedRateString = "${exchangerate.interval}")
    public void getCurrencyExchangeRate()
    {
        ExchangeRateData latestRate = exchangeRateService.getLatestRateFromPublicApi();
        ExchangeRate exchangeRate = defaultExchangeRateMapper.mapExchangeRate(latestRate);
        exchangeRateRepository.save(exchangeRate);
    }
}
