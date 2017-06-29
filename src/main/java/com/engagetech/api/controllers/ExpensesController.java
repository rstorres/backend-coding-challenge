package com.engagetech.api.controllers;


import com.engagetech.api.dao.ExpensesDao;
import com.engagetech.api.dto.Expense;
import com.engagetech.api.exceptions.AmountInvalidFormatException;
import com.engagetech.api.exceptions.ConversionRateNotFoundException;
import com.engagetech.api.model.ExpenseModel;
import com.engagetech.api.services.CurrencyConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by rtorres on 26/06/2017.
 */
@Controller
@RequestMapping("/app/expenses")
public class ExpensesController extends BaseController{

    private static final Logger logger = Logger.getLogger(ExpensesController.class);

    private static final String AMOUNT_REGEXP = "^(\\d*\\.?\\d*)(\\s*)([A-Z]{3})?$";
    private static final String DEFAULT_CURRENCY = "GBP";

    @Autowired
    ExpensesDao expensesDao;

    @RequestMapping( method = RequestMethod.GET)
    public @ResponseBody
    List<Expense> getExpenses() {

        logger.info("Getting expenses");
        return expensesDao.getExpenses()
                .stream()
                .map(Expense::fromExpenseModel)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void save(@RequestBody Expense e) throws AmountInvalidFormatException,ConversionRateNotFoundException {

        float amount;
        String currency;
        float convertedAmount;

        //Checking if a currency is defined in the amount
        Pattern r = Pattern.compile(AMOUNT_REGEXP);
        Matcher m = r.matcher(e.getAmount().toUpperCase());

        logger.info("Checking the amount format");
        if (m.find()) {
            amount = new Float(m.group(1)).floatValue();
            currency = m.group(3);
            logger.info("The amount has a valid format (amount:'"+amount+"'  |  currency:'" + currency + "')");
        } else {
            throw new AmountInvalidFormatException("The amount '"+e.getAmount()+"' has an invalid format");
        }

        //If the currency is defined and if it is different of GBP, the amount will be converted to GBP
        if(currency!= null && !currency.isEmpty() && !currency.equals(DEFAULT_CURRENCY)){
            logger.info("Converting the amount to GBP");
            convertedAmount = CurrencyConverter.convert(amount,currency);
        }
        else{
            convertedAmount = amount;
        }


        expensesDao.saveExpense(new ExpenseModel()
                                .setExpenseDate(e.getDate())
                                .setAmount(convertedAmount)
                                .setReason(e.getReason()));

    }
}




