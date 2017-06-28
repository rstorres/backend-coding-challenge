package com.engagetech.api.services.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rtorres on 28/06/2017.
 */
public class ConversionRate {
    String base;
    Date date;
    Map<String,Float> rates = new HashMap<String,Float>();

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public Map<String,Float> getRates() {
        return rates;
    }

    public void setRates(Map<String,Float> rates) {
        this.rates = rates;
    }

}
