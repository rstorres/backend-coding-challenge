package com.engagetech.api.dto;

import com.engagetech.api.model.ExpenseModel;
import com.engagetech.api.serializers.CustomJsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rtorres on 26/06/2017.
 */
public class Expense {

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date  date;
    private float  amount;
    private float  vat;
    private String reason;

    public Date getDate() {
        return date;
    }

    public Expense setDate(Date expenseDate) {
        this.date = expenseDate;
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public Expense setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    public float getVat() {
        return vat;
    }

    public Expense setVat(float vat) {
        this.vat = vat;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Expense setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public static Expense fromExpenseModel(ExpenseModel e){


        return new Expense()
                .setDate(e.getExpenseDate())
                .setAmount(e.getAmount())
                .setVat(e.getVat())
                .setReason(e.getReason());
    }
}
