package com.engagetech.api.model;

import java.util.Date;

/**
 * Created by rtorres on 27/06/2017.
 */
public class ExpenseModel {

    private Date   expenseDate;
    private float  amount;
    private float  vat;
    private String reason;

    public Date getExpenseDate() {
        return expenseDate;
    }

    public ExpenseModel setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public ExpenseModel setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    public float getVat() {
        return vat;
    }

    public ExpenseModel setVat(float vat) {
        this.vat = vat;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public ExpenseModel setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
