package DhauEmbunAzzahraJmartPK;


public abstract class Transaction extends Recognizable{
    public String time = "TIME";
    public int buyerId;
    public int storeId;
    public Rating rating;
    
    public enum Rating{
        NONE, BAD, NEUTRAL, GOOD;
    }
    
    protected Transaction(int id, int buyerId, int storeId){
        super(id);
        this.buyerId = buyerId;
        this.storeId = storeId;
        this.rating = Rating.NONE;
    }
    
    protected Transaction(int id, Account buyer, Store store){
        super(id);
        this.rating = Rating.NONE;
    }
    
    public boolean validate(){
        return true;
    }
    
    public Transaction perform(){
        return null;
    }

}