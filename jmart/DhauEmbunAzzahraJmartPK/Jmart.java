package DhauEmbunAzzahraJmartPK;


public class Jmart
{
         public static int getPromo(){
        return 0;
    }
    
    public static String getCustomer(){
        return "oop";
}
    public static float  getDiscountPercentage(int before, int after){
        float hasil;
   
        if (before<after){
            return 0.0f;
        }
        else{
            hasil = ((float)(after-before)/(float)before)*100;
            return hasil*(-1);
        }
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        if (discountPercentage>=100.0f){
            return 0;
        }
        else{
            return (int)((100.0f-discountPercentage)*price/100);
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        return (int) ((100+discountPercentage)*discountedPrice)/100;
    }
    public static float getCommissionMultiplier(){
        return 0.05f;
    }
    public static int getAdjustedPrice(int price){
        return (int) 1.05*price;
    }
    public static int getAdminFee(int price){
        return (int) (price*getCommissionMultiplier());
    }
    public static void main(String[] args) {
        System.out.println(getDiscountPercentage(1000, 0));
        System.out.println(getDiscountedPrice(1000, 10.0f));
    }
}
