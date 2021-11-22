package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.Invoice;
import com.DhauEmbunAzzahraJmartPK.ObjectPoolThread;
import com.DhauEmbunAzzahraJmartPK.Payment;
import com.DhauEmbunAzzahraJmartPK.Shipment;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    public static long DELIVERED_LIMIT_MS = 3000;
    public static long ON_DELIVERY_LIMIT_MS = 3000;
    public static long ON_PROGRESS_LIMIT_MS = 3000;
    public static long WAITING_CONF_LIMIT_MS = 1000;
    public static ObjectPoolThread<Payment> poolThread;

    static {
        poolThread = new ObjectPoolThread<>("Thread-PP", PaymentController::timekeeper);
        poolThread.start();
    }

    public static boolean timekeeper(Payment payment){
        Payment.Record record = payment.history.get(payment.history.size()-1);
        long start = record.date.getTime();
        long end = System.nanoTime();
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
    public Payment create(int buyerId,
                          int productId,
                          int productCount,
                          String shipmentAddress,
                          byte shipmentPlan){
        return new Payment(buyerId,productId,productCount,new Shipment(shipmentAddress,2000, shipmentPlan, "receipt"));
    }

    @PostMapping(value = "/{id}/accept")
    public boolean accept(int id){
        return true;
    }

    @PostMapping(value = "/{id}/cancel")
    public boolean cancel(int id){
        return true;
    }

    @PostMapping(value = "/{id}/submit")
    public boolean submit(int id, String receipt){
        return true;
    }



    @JsonAutowired(value = Payment.class, filepath = "C://Proyek Jmart/Jmart/lib/payment.json")
    public static JsonTable<Payment> paymentTable;


    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

}
