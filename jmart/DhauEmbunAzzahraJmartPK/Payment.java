package DhauEmbunAzzahraJmartPK;


public class Payment extends Transaction implements FileParser{
    public int productId;
    public ShipmentDuration shipmentDuration;
    
    public Payment(int id, int buyerId, Product product, ShipmentDuration shipmentDuration){
        super(id, buyerId, product.id);
        this.shipmentDuration = shipmentDuration;
    }
    
    public Payment(int id, int buyerId, int storeId, 
                    int productId, ShipmentDuration shipmentDuration){
        super(id, buyerId, storeId);
        this.productId = productId;
        this.shipmentDuration = shipmentDuration;
    }
    
    @Override
    public boolean validate()  {
        return true;
    }  
    @Override
    public Transaction perform(){
        return null;
    }
    
    @Override
    public boolean read(String content){
        return true;
    }

    @Override
    public Object write() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}