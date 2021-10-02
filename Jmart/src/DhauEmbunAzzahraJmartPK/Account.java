package DhauEmbunAzzahraJmartPK;


public class Account extends Recognizable implements FileParser{
    public String name;
    public String email;
    public String password;
    
    public Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
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
        return "name: "+this.name+"\nemail: "+this.email+"\npassword: "+this.password;
    }
    
}