package com.searchmetrics.currency.repository;

import com.searchmetrics.currency.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Slavko Lozić (slozic1@gmail.com))
 */
@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long>
{
    /**
     * Returns latest exchange rate ordered by timestamp.
     *
     * @return ExchangeRate
     */
    ExchangeRate findFirstByOrderByTimestampDesc();

    /**
     * Returns exchange rates between start and end dates.
     *
     * @param startDate
     * @param endDate
     * @return List<ExchangeRate>
     */
    List<ExchangeRate> findAllByDateBetween(String startDate, String endDate);
}
