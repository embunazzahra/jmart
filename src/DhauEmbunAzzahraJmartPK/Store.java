package DhauEmbunAzzahraJmartPK;

import java.util.regex.Pattern;

public class Store extends Recognizable implements FileParser{
    public String name;
    public String address;
    public String phoneNumber;
    public static final String REGEX_PHONE = "[0-9]{9,12}";
    public static final String REGEX_NAME = "([A-Z])([\\s\\S](?!.*[\\s]{2,})){3,19}";
        
    public boolean validate(){
        return (Pattern.matches(REGEX_PHONE, phoneNumber)) && (Pattern.matches(REGEX_NAME, name));
    }
    
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
    
    public String toString(){
        return "name: "+this.name+"\naddress: "+this.address+"\nphoneNumber: "+this.phoneNumber;
    }

}