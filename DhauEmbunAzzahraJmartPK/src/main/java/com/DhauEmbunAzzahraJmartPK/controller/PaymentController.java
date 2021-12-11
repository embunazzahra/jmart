package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.*;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    public static long DELIVERED_LIMIT_MS = 60000;
    public static long ON_DELIVERY_LIMIT_MS = 60000;
    public static long ON_PROGRESS_LIMIT_MS = 60000;
    public static long WAITING_CONF_LIMIT_MS = 60000;
    public static ObjectPoolThread<Payment> poolThread;

    static {
        poolThread = new ObjectPoolThread<>("Thread-PP", PaymentController::timekeeper);
        poolThread.start();
    }

    public static boolean timekeeper(Payment payment){
        Payment.Record record = payment.history.get(payment.history.size()-1);
        long start = record.date.getTime();
        long end = System.currentTimeMillis();
        long elapsed = end - start;

        if (record.status == Invoice.Status.WAITING_CONFIRMATION && elapsed > WAITING_CONF_LIMIT_MS){
            Payment.Record newRecord = new Payment.Record(Invoice.Status.FAILED,"failed");
            payment.history.add(newRecord);
            return true;
        }
        else if(record.status == Invoice.Status.ON_PROGRESS && elapsed > ON_PROGRESS_LIMIT_MS){
            Payment.Record newRecord = new Payment.Record(Invoice.Status.FAILED,"failed");
            payment.history.add(newRecord);
            return true;
        }
        else if(record.status == Invoice.Status.ON_DELIVERY && elapsed > ON_DELIVERY_LIMIT_MS){
            Payment.Record newRecord = new Payment.Record(Invoice.Status.DELIVERED,"delivered");
            payment.history.add(newRecord);
            return false;
        }
        else if(record.status == Invoice.Status.DELIVERED && elapsed > DELIVERED_LIMIT_MS){
            Payment.Record newRecord = new Payment.Record(Invoice.Status.FINISHED,"finished");
            payment.history.add(newRecord);
            return true;
        }
        else {
            return false;
        }
    }

    @PostMapping(value = "/create")
    public Payment create(@RequestParam int buyerId,
                          @RequestParam int productId,
                          @RequestParam int productCount,
                          @RequestParam String shipmentAddress,
                          @RequestParam byte shipmentPlan){
        Account account = Algorithm.<Account>find(AccountController.accountTable, e->e.id==buyerId);
        Product product = Algorithm.<Product>find(ProductController.productTable, e->e.id==productId);
        if(account!=null && product!=null){
            Payment payment = new Payment(buyerId,
                    productId,
                    productCount,
                    new Shipment(shipmentAddress,10000,shipmentPlan, null));
            double total = payment.getTotalPay(product);
            if(account.balance>=total){
                account.balance-=total;
                Payment.Record record = new Payment.Record(Invoice.Status.WAITING_CONFIRMATION,
                        "waiting confirmation");
                payment.history.add(record);
                paymentTable.add(payment);
                poolThread.add(payment);
                return payment;

            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @PostMapping(value = "/{id}/accept")
    public boolean accept(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(getJsonTable(),e->e.id==id);
        if(payment!=null &&
                payment.history
                .get(payment.history.size()-1)
                .status
                .equals(Invoice.Status.WAITING_CONFIRMATION)){
            Payment.Record newRecord = new Payment.Record(Invoice.Status.ON_PROGRESS,"on progress");
            payment.history.add(newRecord);
            return true;
        }
        return false;
    }

    @PostMapping(value = "/{id}/cancel")
    public boolean cancel(@PathVariable int id){

        Payment payment = Algorithm.<Payment>find(getJsonTable(),e->e.id==id);
        if(payment!=null &&
                payment.history
                        .get(payment.history.size()-1)
                        .status
                        .equals(Invoice.Status.WAITING_CONFIRMATION)){
            Payment.Record newRecord = new Payment.Record(Invoice.Status.CANCELLED,"cancelled");
            payment.history.add(newRecord);
            return true;
        }
        return false;
    }

    @PostMapping(value = "/{id}/submit")
    public boolean submit(@PathVariable int id, @RequestParam String receipt){
        Payment payment = Algorithm.<Payment>find(getJsonTable(),e->e.id==id);
        if(payment!=null &&
                payment.history
                        .get(payment.history.size()-1)
                        .status
                        .equals(Invoice.Status.ON_PROGRESS) &&
                !payment.shipment.receipt.isBlank()){
            payment.shipment.receipt = receipt;
            Payment.Record newRecord = new Payment.Record(Invoice.Status.ON_DELIVERY,"on delivery");
            payment.history.add(newRecord);
            return true;
        }
        return false;
    }

    @JsonAutowired(value = Payment.class, filepath = "C://Proyek Jmart/Jmart/lib/payment.json")
    public static JsonTable<Payment> paymentTable;


    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

}
