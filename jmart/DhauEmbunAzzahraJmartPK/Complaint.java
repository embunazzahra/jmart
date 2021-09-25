package DhauEmbunAzzahraJmartPK;




public class Complaint extends Transaction implements FileParser{
    public int paymentId;
    public String desc;
    
    public Complaint(int id, Payment payment, String desc){
        super(id, payment.buyerId, payment.storeId);
        this.paymentId = payment.id;
        this.desc = desc;
    }
    
    public Complaint(int id, int buyerId, int storeId, int paymentId, String desc){
        super(id, buyerId, storeId);
        this.paymentId = paymentId;
        this.desc = desc;
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
        return null;
    }
}