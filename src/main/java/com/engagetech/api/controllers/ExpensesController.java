package com.engagetech.api.controllers;


import com.engagetech.api.dao.ExpensesDao;
import com.engagetech.api.dto.Expense;
import com.engagetech.api.model.ExpenseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rtorres on 26/06/2017.
 */
@Controller
@RequestMapping("/app/expenses")
public class ExpensesController {

    @Autowired
    ExpensesDao expensesDao;

    @RequestMapping( method = RequestMethod.GET)
    public @ResponseBody
    List<Expense> getExpenses() {

        return expensesDao.getExpenses()
                .stream()
                .map(Expense::fromExpenseModel)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void save(@RequestBody Expense e) {

        expensesDao.saveExpense(new ExpenseModel()
                                .setExpenseDate(e.getDate())
                                .setAmount(e.getAmount())
                                .setVat(e.getVat())
                                .setReason(e.getReason()));

    }
}




