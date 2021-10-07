package DhauEmbunAzzahraJmartPK;


public class Payment extends Invoice implements Transactor{
    public int productCount;
    public Shipment shipment;
    public Payment(int id, int buyerId, int productId, int productCount, Shipment shipment){
        super(id,buyerId,productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }
    
    @Override
    public boolean validate()  {
        return true;
    }  
    @Override
    public Invoice perform(){
        return null;
    }
    @Override
    public double getTotalPay(){
        return 0.0;
    }
}