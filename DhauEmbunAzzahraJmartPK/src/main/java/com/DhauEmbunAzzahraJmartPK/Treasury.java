package com.DhauEmbunAzzahraJmartPK;


/**
 * This is class for representing a treasury
 *
 * @author Dhau' Embun Azzahra
 * */
public class Treasury {
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;

    /**
     * Method to get the sum of the admin fee and product price after discount
     * @param price product price
     * @param discount product discount
     * @return sum of the admin fee and product price after discount
     */
    public static double getAdjustedPrice(double price, double discount){

        return getDiscountedPrice(price, discount)+ getAdminFee(price, discount);
    }

    /**
     * Method to get the admin fee
     * @param price product price
     * @param discount product discount
     * @return the admin fee
     */
    public static double getAdminFee(double price, double discount){
        double discountedPrice = getDiscountedPrice(price, discount);
        if(discountedPrice<BOTTOM_PRICE){
            return BOTTOM_FEE;
        }
        else{
            return (COMMISSION_MULTIPLIER)*discountedPrice;
        }
        
    }

    /**
     * Method to get discounted price
     * @param price product price
     * @param discount product discount
     * @return the discounted price
     */
    public static double getDiscountedPrice(double price, double discount){
        if (discount>=100.0){
            return 0.0;
        }
        else{
            return (100.0-discount)*price/100.0;
        }
    }
    
}
