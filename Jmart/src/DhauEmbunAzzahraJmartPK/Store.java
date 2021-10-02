package DhauEmbunAzzahraJmartPK;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store extends Recognizable implements FileParser{
    public String name;
    public String address;
    public String phoneNumber;
    public static String REGEX_PHONE = "[0-9]{9,12}";
    public static String REGEX_NAME = "^[A-Z][A-Za-z]{4,20}";
        
    public boolean validate(){
        Pattern phone_pattern = Pattern.compile(REGEX_PHONE);
        Matcher phone_matcher = phone_pattern.matcher(phoneNumber);
        Pattern name_pattern = Pattern.compile(REGEX_NAME);
        Matcher name_matcher = name_pattern.matcher(name);
        if ((phone_matcher.find()==true) &&(name_matcher.find()==true)){
            return true;
        }
        else{
            return false;
        }
        
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