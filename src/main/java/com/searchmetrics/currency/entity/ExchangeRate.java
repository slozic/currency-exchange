package com.searchmetrics.currency.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExchangeRate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long timestamp;
    private String date;
    private String base;
    private String currency;
    private String rate;

    public ExchangeRate() {}

    public ExchangeRate(long timestamp, String date, String base, String currency, String rate)
    {
        this.timestamp = timestamp;
        this.date = date;
        this.base = base;
        this.currency = currency;
        this.rate = rate;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getBase()
    {
        return base;
    }

    public void setBase(String base)
    {
        this.base = base;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getRate()
    {
        return rate;
    }

    public void setRate(String rate)
    {
        this.rate = rate;
    }

    @Override
    public String toString()
    {
        return String.format(
                "ExchangeRate[timestamp=%s, date='%s', base='%s', currency='%s', rate=%s]",
                timestamp,
                date,
                base,
                currency,
                rate
        );
    }
}
