package DhauEmbunAzzahraJmartPK;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Complaint extends Recognizable implements FileParser{
    public Date date = new Date();
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

    @Override
    public String toString(){
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        return "date="+date_format.format(date)+",desc='"+desc+"'";
    }
}