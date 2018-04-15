package com.searchmetrics.currency.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Slavko Lozić (slozic1@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateData implements Serializable
{
    private String base;
    private String date;
    private Map<String, String> rates;

    public String getBase()
    {
        return base;
    }

    public void setBase(String base)
    {
        this.base = base;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public Map<String, String> getRates()
    {
        return rates;
    }

    public void setRates(Map<String, String> rates)
    {
        this.rates = rates;
    }
}
