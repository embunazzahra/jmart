package DhauEmbunAzzahraJmartPK;


public class Jmart
{
    public static Product createProduct(){
        return new Product("Embun", 8, true, new PriceTag(70.0), ProductCategory.BOOK);
    }
    
    public static Coupon createCoupon(){
        return null;
    }
    
    public static ShipmentDuration createShipmentDuration(){
        return null;
    }
    
    public static void main(String[] args) 
    {
        
                
    }
}
