package com.engagetech.api.dao.impl;

import com.engagetech.api.dao.ExpensesDao;
import com.engagetech.api.model.ExpenseModel;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rtorres on 27/06/2017.
 */
public class ExpensesDaoImpl implements ExpensesDao {

    private static final String GET_EXPENSES_SQL = "select expense_date, amount, vat, reason from expenses";

    private static final String INSERT_EXPENSE_SQL = "insert into expenses(expense_date,amount,vat,reason) values(:expenseDate,:amount,:vat,:reason)";

    private static final float VAT_RATE = 0.20f;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate template) {
        this.namedParameterJdbcTemplate = template;
    }

    public List<ExpenseModel> getExpenses(){

       return namedParameterJdbcTemplate.query(
                GET_EXPENSES_SQL,
                (rs,rowNum)-> {
                    return new ExpenseModel()
                            .setExpenseDate(rs.getDate("expense_date"))
                            .setAmount(rs.getFloat("amount"))
                            .setVat(rs.getFloat("vat"))
                            .setReason(rs.getString("reason"));
                });
    }

    public void saveExpense(ExpenseModel e){

        float vat = BigDecimal.valueOf(VAT_RATE * e.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

        Map namedParameters = new HashMap();
        namedParameters.put("expenseDate", e.getExpenseDate());
        namedParameters.put("amount", e.getAmount());
        namedParameters.put("vat", vat);
        namedParameters.put("reason",e.getReason());
        namedParameterJdbcTemplate.update(INSERT_EXPENSE_SQL, namedParameters);
    }

}
