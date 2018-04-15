package com.searchmetrics.currency.repository;

import com.searchmetrics.currency.entity.ExchangeRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExchangeRateRepositoryTest
{
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findFirstByOrderByTimestampDesc()
    {
        //given
        ExchangeRate testExchangeRate = this.testEntityManager.persist(new ExchangeRate(
                System.currentTimeMillis(),
                "2018-04-10",
                "EUR",
                "USD",
                "1.234567"
        ));
        ExchangeRate testExchangeRate1 = this.testEntityManager.persist(new ExchangeRate(
                System.currentTimeMillis(),
                "2018-04-10",
                "EUR",
                "USD",
                "1.234567"
        ));

        //when
        ExchangeRate exchangeRate = exchangeRateRepository.findFirstByOrderByTimestampDesc();

        //then
        assertThat(exchangeRate.equals(testExchangeRate1));

    }

    @Test
    public void findAllByDateBetween()
    {
        //given
        ExchangeRate testExchangeRate = this.testEntityManager.persist(new ExchangeRate(
                System.currentTimeMillis(),
                "2018-04-10",
                "EUR",
                "USD",
                "1.234567"
        ));
        ExchangeRate testExchangeRate1 = this.testEntityManager.persist(new ExchangeRate(
                System.currentTimeMillis(),
                "2018-04-11",
                "EUR",
                "USD",
                "1.234567"
        ));
        ExchangeRate testExchangeRate2 = this.testEntityManager.persist(new ExchangeRate(
                System.currentTimeMillis(),
                "2018-04-12",
                "EUR",
                "USD",
                "1.234567"
        ));
        ExchangeRate testExchangeRate3 = this.testEntityManager.persist(new ExchangeRate(
                System.currentTimeMillis(),
                "2018-04-13",
                "EUR",
                "USD",
                "1.234567"
        ));

        //when
        List<ExchangeRate> exchangeRates = exchangeRateRepository.findAllByDateBetween("2018-04-11", "2018-04-12");

        //then
        assertThat(exchangeRates).hasSize(2);
        assertThat(exchangeRates).contains(testExchangeRate1, testExchangeRate2);
    }
}