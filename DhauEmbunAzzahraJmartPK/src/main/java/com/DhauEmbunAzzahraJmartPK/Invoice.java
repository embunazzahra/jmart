package com.DhauEmbunAzzahraJmartPK;


import com.DhauEmbunAzzahraJmartPK.dbjson.Serializable;

import java.util.Date;

public class Invoice extends Serializable {
    public final Date date = new Date();
    public int buyerId;
    public int productId;
    public int complaintId = -1;
    public Rating rating = Rating.NONE;
    public Status status;
    
    public enum Status{
        WAITING_CONFIRMATION,
        CANCELLED,
        DELIVERED,
        ON_PROGRESS,
        ON_DELIVERY,
        COMPLAINT,
        FINISHED,
        FAILED;
    }
    
    public enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD;
    }
    
    protected Invoice(int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
    }

    public double getTotalPay(Product product){
        return product.price;
    }

}