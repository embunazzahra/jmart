package com.DhauEmbunAzzahraJmartPK;

import java.util.regex.Pattern;

public class Store {
    public String name;
    public String address;
    public String phoneNumber;
    public static final String REGEX_PHONE = "^(/d{9,12})$";
    public static final String REGEX_NAME = "^(?=^[A-Z])(?![A-Z a-z]{20,})((?=[A-Z a-z]{4,}).)((?!\\s{2}).)*$";
        
    public boolean validate(){
        return (Pattern.matches(REGEX_PHONE, phoneNumber)) && (Pattern.matches(REGEX_NAME, name));
    }
    
    public Store(String name, String address, String phoneNumber){
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString(){
        return "name: "+this.name+"\naddress: "+this.address+"\nphoneNumber: "+this.phoneNumber;
    }

}