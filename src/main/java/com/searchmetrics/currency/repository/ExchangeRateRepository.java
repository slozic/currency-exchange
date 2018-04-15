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
    ExchangeRate findFirstByOrderByTimestampDesc();

    List<ExchangeRate> findAllByDateBetween(String startDate, String endDate);
}
