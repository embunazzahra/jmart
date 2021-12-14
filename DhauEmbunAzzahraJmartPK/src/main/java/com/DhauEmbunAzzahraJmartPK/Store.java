package com.DhauEmbunAzzahraJmartPK;

import java.util.regex.Pattern;

/**
 * This is class for representing a store
 *
 * @author Dhau' Embun Azzahra
 * */
public class Store {
    public String name;
    public String address;
    public String phoneNumber;
    public static final String REGEX_PHONE = "^(/d{9,12})$";
    public static final String REGEX_NAME = "^(?=^[A-Z])(?![A-Z a-z]{20,})((?=[A-Z a-z]{4,}).)((?!\\s{2}).)*$";

    /**
     * Method to validate store's phone number and name with certain regex
     * @return true if phone number and name is matched from the regex, otherwise false.
     */
    public boolean validate(){
        return (Pattern.matches(REGEX_PHONE, phoneNumber)) && (Pattern.matches(REGEX_NAME, name));
    }

    /**
     * Creates a store.
     * @param name Store name
     * @param address Store address
     * @param phoneNumber Store phone number
     */
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