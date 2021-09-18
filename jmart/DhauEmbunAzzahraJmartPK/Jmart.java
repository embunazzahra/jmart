package DhauEmbunAzzahraJmartPK;


public class Jmart
{
    public static Product create(){
        return new Product("Embun", 8, true, new PriceTag(70.0), ProductCategory.BOOK);
    }
    public static void main(String[] args) 
    {
        
                
    }
}
