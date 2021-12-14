package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.*;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * For payment controller
 * @author Dhau' Embun Azzahra
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    /**
     * Time of wait in milliseconds.
     */
    public static long DELIVERED_LIMIT_MS = 60000;
    public static long ON_DELIVERY_LIMIT_MS = 60000;
    public static long ON_PROGRESS_LIMIT_MS = 120000;
    public static long WAITING_CONF_LIMIT_MS = 120000;
    public static ObjectPoolThread<Payment> poolThread;

    static {
        poolThread = new ObjectPoolThread<>("Thread-PP", PaymentController::timekeeper);
        poolThread.start();
    }

    /**
     * Time keeper of order track.
     * Automatically add new order history if
     * the last one exceeds the specified time limit
     * @param payment the payment to check.
     * @return false after add new "delivered" status in history, otherwise false.
     */
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


    /**
     * For post request to make a payment.
     * @param buyerId buyer id.
     * @param productId product id.
     * @param productCount product count.
     * @param shipmentAddress buyer's shipment address
     * @param shipmentPlan shipment plan.
     * @return payment after created.
     */
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
                    new Shipment(shipmentAddress,10000,shipmentPlan, " "));
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


    /**
     * For post request to accept a buyer's payment.
     * Used by the store to continue the order.
     * if the store doesn't accept the payment until
     * exceeds the time limit, the order will be failed.
     * @param id payment id.
     * @return true if success, otherwise false.
     */
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

    /**
     * For post request to cancel payment when the last
     * history status is "waiting confirmation".
     * This can be done by store or buyer.
     * @param id payment id.
     * @return true if success, otherwise false.
     */
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

    /**
     * For post request to submit receipt if the product has
     * just been in the courier.
     * This can be done by the store after accept the buyer's payment.
     * @param id payment id.
     * @param receipt the receipt.
     * @return true if success, otherwise false.
     */
    @PostMapping(value = "/{id}/submit")
    public boolean submit(@PathVariable int id, @RequestParam String receipt){
        Payment payment = Algorithm.<Payment>find(getJsonTable(),e->e.id==id);
        if(payment!=null &&
                payment.history
                        .get(payment.history.size()-1)
                        .status
                        .equals(Invoice.Status.ON_PROGRESS) &&
                payment.shipment.receipt.isBlank()){
            payment.shipment.receipt = receipt;
            Payment.Record newRecord = new Payment.Record(Invoice.Status.ON_DELIVERY,"on delivery");
            payment.history.add(newRecord);
            return true;
        }
        return false;
    }

    /**
     * For get request to get all the payment list of a buyer.
     * @param buyerId buyer id
     * @return all the payment list of a buyer.
     */
    @GetMapping(value = "/byAccount")
    public ArrayList<Payment> getByAccount(@RequestParam int buyerId){
        ArrayList<Payment> paymentArrayListList = new ArrayList<>();
        List<Payment> list = Algorithm.<Payment>collect(getJsonTable(),e->e.buyerId==buyerId);
        paymentArrayListList.addAll(list);
        return paymentArrayListList;
    }

    /**
     * For get request to get all the payment list with
     * certain products id.
     * Can be use to collect all payment with many product id at once.
     * @param productId list of product id.
     * @return list of payment with certain product id.
     */
    @GetMapping(value = "/byStore")
    public ArrayList<Payment> getByStore(@RequestParam ArrayList<Integer> productId){
        ArrayList<Payment> paymentArrayListList = new ArrayList<>();
        for(int i=0;i<productId.size();i++){

        }
        List<Payment> list = Algorithm.<Payment>collect(getJsonTable(),e->productId.contains(e.productId));
        paymentArrayListList.addAll(list);
        return paymentArrayListList;
    }


    /**
     * The json table of payment class.
     * save locally in this path.
     */
    @JsonAutowired(value = Payment.class, filepath = "C://Proyek Jmart/Jmart/lib/payment.json")
    public static JsonTable<Payment> paymentTable;


    /**
     * Method to get payment Json Table
     * @return payment Json Table
     */
    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

}
