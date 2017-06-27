package com.engagetech.api.dao;

import com.engagetech.api.model.ExpenseModel;

import java.util.List;

/**
 * Created by rtorres on 27/06/2017.
 */
public interface ExpensesDao {

    List<ExpenseModel> getExpenses();

    void saveExpense(ExpenseModel expense);
}
