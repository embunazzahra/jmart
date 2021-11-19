package com.DhauEmbunAzzahraJmartPK;



public class Coupon extends Serializable
{
    public final String name;
    public final int code;
    public final Type type;
    public final double cut;
    public final double minimum;
    private boolean used;

    public enum Type{
        DISCOUNT,
        REBATE
    }
    
    public Coupon(String name, int code, Type type, double cut, double
minimum){
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }
    
    public boolean isUsed(){
        return used;
    }

    public boolean canApply(Treasury treasury){
        if (treasury.getAdjustedPrice(100000, 50)>=minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }
    
    public double apply(Treasury treasury){
        used = true;
        if (type == Type.DISCOUNT){
            return treasury.getAdjustedPrice(100000, 50) * (100.0-cut)/100;
        }
        else{
            return treasury.getAdjustedPrice(100000, 50) - cut;
        }
    }
}