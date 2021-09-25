package DhauEmbunAzzahraJmartPK;




public class Complaint extends Transaction implements FileParser{
    public int paymentId;
    public String desc;
    
    public Complaint(int id, Payment payment, String desc){
        //super(id);
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
}
