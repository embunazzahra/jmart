package com.DhauEmbunAzzahraJmartPK;


import com.DhauEmbunAzzahraJmartPK.dbjson.Serializable;

/**
 * This is class for representing a coupon
 *
 * @author Dhau' Embun Azzahra
 * */
public class Coupon extends Serializable
{
    public final String name;
    public final int code;
    public final Type type;
    public final double cut;
    public final double minimum;
    private boolean used;

    /**
     * The type of coupon. DISCOUNT type is percentage type in range 0-100. REBATE type is the rebate.
     */
    public enum Type{
        DISCOUNT,
        REBATE
    }

    /**
     * Creates a coupon with the specified information.
     * @param name Coupon's name.
     * @param code Coupon's code.
     * @param type Coupon's type.
     * @param cut Coupon's cut.
     * @param minimum Coupon's minimum.
     */
    public Coupon(String name, int code, Type type, double cut, double
minimum){
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }

    /**
     * Method to get coupon's condition use
     * @return true if used, otherwise false.
     */
    public boolean isUsed(){
        return used;
    }

    /**
     * Method to validate if the coupon can be applied or not.
     * @param price Product's price
     * @param discount The product's discount
     * @return true if coupon can be applied, false otherwise.
     */
    public boolean canApply(double price, double discount){
        if (Treasury.getAdjustedPrice(price, discount)>=minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Method to apply the coupon
     * @param price Product's price
     * @param discount The product's discount
     * @return the total pay after the coupon is applied.
     */
    public double apply(double price, double discount){
        used = true;
        if (type == Type.DISCOUNT){
            return Treasury.getAdjustedPrice(price, discount) * (100.0-cut)/100;
        }
        else{
            return Treasury.getAdjustedPrice(price, discount) - cut;
        }
    }
}