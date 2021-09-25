package DhauEmbunAzzahraJmartPK;


public class Store extends Recognizable implements FileParser{
    public String name;
    public String address;
    public String phoneNumber;
    
    public Store(int accountId, String name, String address, String phoneNumber){
        super(accountId);
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public Store(Account account, String name, String address, String phoneNumber){
        super(account.id);
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean read(String content) {
        return true;
    }

    @Override
    public Object write() {
        return null;
    }
    

}

