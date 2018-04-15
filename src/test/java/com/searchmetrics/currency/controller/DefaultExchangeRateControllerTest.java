package com.searchmetrics.currency.controller;

import com.searchmetrics.currency.entity.ExchangeRate;
import com.searchmetrics.currency.service.ExchangeRateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Slavko Lozić (slozic1@gmail.com)
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DefaultExchangeRateController.class)
public class DefaultExchangeRateControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @Test
    public void getLatestRate() throws Exception
    {
        ExchangeRate rate = new ExchangeRate();
        rate.setTimestamp(System.currentTimeMillis());
        rate.setDate("2018-04-15");
        rate.setBase("EUR");
        rate.setCurrency("USD");
        rate.setRate("1.23456");

        given(this.exchangeRateService.getLatestRate()).willReturn(rate);

        this.mvc.perform(get("/latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(rate.getDate())))
                .andExpect(jsonPath("$.rate", is(rate.getRate())));
    }

    @Test
    public void getRatesBetweenDates() throws Exception
    {
        ExchangeRate rate = new ExchangeRate();
        rate.setTimestamp(System.currentTimeMillis());
        rate.setDate("2018-04-15");
        rate.setBase("EUR");
        rate.setCurrency("USD");
        rate.setRate("1.23456");

        ExchangeRate rate1 = new ExchangeRate();
        rate1.setTimestamp(System.currentTimeMillis());
        rate1.setDate("2018-04-16");
        rate1.setBase("EUR");
        rate1.setCurrency("USD");
        rate1.setRate("1.33456");

        ExchangeRate rate2 = new ExchangeRate();
        rate2.setTimestamp(System.currentTimeMillis());
        rate2.setDate("2018-04-17");
        rate2.setBase("EUR");
        rate2.setCurrency("USD");
        rate2.setRate("1.43456");

        List<ExchangeRate> exchangeRates = Arrays.asList(rate1);

        given(this.exchangeRateService.getHistoricalRates(rate1.getDate(), rate1.getDate())).willReturn(exchangeRates);

        this.mvc.perform(get("/timeseries")
                .param("startDate", rate1.getDate())
                .param("endDate", rate1.getDate()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date", is(rate1.getDate())))
                .andExpect(jsonPath("$[0].rate", is(rate1.getRate())));
    }
}