package com.searchmetrics.currency.service.impl;

import com.searchmetrics.currency.data.ExchangeRateData;
import com.searchmetrics.currency.repository.ExchangeRateRepository;
import com.searchmetrics.currency.service.ExchangeRateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@RestClientTest(ExchangeRateService.class)
public class DefaultExchangeRateServiceTest
{
    @Autowired
    private ExchangeRateService exchangeRateService;

    @MockBean
    private ExchangeRateRepository exchangeRateRepository;

    @Test
    public void getLatestRateFromPublicApi()
    {
        ExchangeRateData exchangeRate = this.exchangeRateService.getLatestRateFromPublicApi();

        assertThat(exchangeRate).isNotNull();
        assertThat(exchangeRate.getBase()).isEqualTo("EUR");
    }
}