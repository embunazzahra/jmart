package DhauEmbunAzzahraJmartPK;



public class Treasury {
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;

    public static double getAdjustedPrice(double price, double discount){

        return getDiscountedPrice(price, discount)+ getAdminFee(price, discount);
    }
    public static double getAdminFee(double price, double discount){
        double discountedPrice = getDiscountedPrice(price, discount);
        if(discountedPrice<BOTTOM_PRICE){
            return BOTTOM_FEE;
        }
        else{
            return (100.0-COMMISSION_MULTIPLIER)*discountedPrice;
        }
        
    }
    public static double getDiscountedPrice(double price, double discount){
        if (discount>=100.0){
            return 0.0;
        }
        else{
            return (100.0-discount)*price/100.0;
        }
    }
    
}
