package com.engagetech.api.controllers;

import com.engagetech.api.exceptions.AmountInvalidFormatException;
import com.engagetech.api.exceptions.ConversionRateNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rtorres on 27/06/2017.
 */
public class BaseController {

    private static final Logger logger = Logger.getLogger(BaseController.class);


    @ExceptionHandler(AmountInvalidFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The amount has an invalid format")
    public void handleAppException(AmountInvalidFormatException ex) {
        logger.error("The amount has an invalid value");
    }


    @ExceptionHandler(ConversionRateNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void handleAppException(ConversionRateNotFoundException ex) {
        logger.error("Conversion rate not found");
    }

}
