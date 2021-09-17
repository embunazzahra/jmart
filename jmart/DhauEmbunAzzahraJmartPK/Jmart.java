package DhauEmbunAzzahraJmartPK;


public class Jmart
{
    public static int getPromo()
    {
        return 0;
    }
    
    public static String getCustomer()
    {
        return "oop";
    }
    public static float  getDiscountPercentage(int before, int after)
    {
        float hasil;
   
        if (before<=after || before == 0)
        {
            return 0.0f;
        }
        else
        {
            hasil = ((float)(before-after)/(float)before)*100;
            return hasil;
        }
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        if (discountPercentage>=100.0f)
        {
            return 0;
        }
        else
        {
            return (int)((100.0f-discountPercentage)*price/100.0f);
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage)
    {
        if (discountPercentage>=100.0f)
        {
            return 0;
        }
        else
        {
            return (int) discountedPrice*100/(int)(100.0f-discountPercentage);
        }
    }
    public static float getCommissionMultiplier()
    {
        return 0.05f;
    }
    public static int getAdjustedPrice(int price)
    {
        return (int) ((getCommissionMultiplier()+1)*price);
    }
    public static int getAdminFee(int price)
    {
        return (int) (price*getCommissionMultiplier());
    }
    public static void main(String[] args) 
    {

                
    }
}
