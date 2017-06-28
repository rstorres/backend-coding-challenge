package com.engagetech.api.services;

import com.engagetech.api.exceptions.ConversionRateNotFoundException;
import com.engagetech.api.services.dto.ConversionRate;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * Created by rtorres on 27/06/2017.
 */
public class CurrencyConverter {

    private static final Logger logger = Logger.getLogger(CurrencyConverter.class);

    private static final String CURRENCY_RATES_URL = "http://api.fixer.io/latest?base=%s&symbols=GBP";


    public static float convert(float amount, String currency) throws ConversionRateNotFoundException{

        logger.info("Getting the conversion rate from 'fixer.io' service");
        RestTemplate restTemplate = new RestTemplate();
        ConversionRate conversionRate = restTemplate.getForObject(String.format(CURRENCY_RATES_URL,currency), ConversionRate.class);

        if(conversionRate.getRates()!=null && conversionRate.getRates().containsKey("GBP")){
            logger.info("Conversion rate found");
            float rate = conversionRate.getRates().get("GBP");

            logger.info("Converting the amount");
            return BigDecimal.valueOf(amount*rate).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        }
        else{
            throw new ConversionRateNotFoundException("It was not found a conversion rate for '"+currency+"'");
        }
    }
}
