package DhauEmbunAzzahraJmartPK;


import java.util.ArrayList;
import java.util.Date;

public class Invoice extends Serializable {
    public final Date date = new Date();
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating = Rating.NONE;
    public Status status = Status.WAITING_CONFIRMATION;
    ArrayList<Record> history = new ArrayList<Record>();
    
    public enum Status{
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
        COMPLAINT,
        FINISHED,
        FAILED;
    }
    
    public enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD;
    }
    
    protected Invoice(int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
    }

    public double getTotalPay(){
        return 0.0;
    }

    public static class Record{
        public Status status;
        public Date date;
        public String message;
    }

}