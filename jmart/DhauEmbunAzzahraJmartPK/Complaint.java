package DhauEmbunAzzahraJmartPK;


public class Complaint extends Recognizable implements FileParser{
    public String date = "Senin";
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.desc = desc;
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