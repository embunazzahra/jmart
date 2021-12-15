package com.DhauEmbunAzzahraJmartPK;

/**
 * This is class for representing a phone top up
 *
 * @author Dhau' Embun Azzahra
 * */
public class PhoneTopUp extends Invoice{
    public String phoneNumber;

    /**
     * Creates a phone top up.
     * @param buyerId The buyer's id
     * @param productId The product's id
     * @param phoneNumber The phone number
     */
    public PhoneTopUp(int buyerId, int productId, String phoneNumber, Status status){
        super(buyerId,productId);
        this.phoneNumber = phoneNumber;
        super.status = status;
    }

    /**
     * Method to get the phone top up's price
     * @param product The product
     * @return the phone top up's price
     */
    @Override
    public double getTotalPay(Product product) {
        return super.getTotalPay(product);
    }
}
