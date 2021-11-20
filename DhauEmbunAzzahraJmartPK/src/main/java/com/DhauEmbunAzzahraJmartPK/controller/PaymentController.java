package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.Account;
import com.DhauEmbunAzzahraJmartPK.Payment;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;

public class PaymentController implements BasicGetController<Payment> {
    @JsonAutowired(value = Payment.class, filepath = "C://Proyek Jmart/Jmart/lib/payment.json")
    public static JsonTable<Payment> paymentTable;
    public static long DELIVERED_LIMIT_MS = 3;
    public static long ON_DELIVERY_LIMIT_MS = 3;
    public static long ON_PROGRESS_LIMIT_MS = 3;
    public static long WAITING_CONF_LIMIT_MS = 10;

    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

}
