package com.DhauEmbunAzzahraJmartPK;


import com.DhauEmbunAzzahraJmartPK.dbjson.Serializable;

import java.util.regex.Pattern;

/**
 * This is class for representing an account
 *
 * @author Dhau' Embun Azzahra
 * */
public class Account extends Serializable {
    public String name;
    public String email;
    public String password;
    public double balance;
    public Store store = null;
    public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{8,}$";

    /** Creates an account with the specified information.
     * @param name The account's name.
     * @param email The account's email address.
     * @param password The account's password.
     * @param balance The account's balance.
     */
    public Account(String name, String email, String password, double balance){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    /**
     * Method to validate account's email and password with certain regex
     * @return true if email and password is matched from the regex, otherwise false.
     */
    public boolean validate(){
        return (Pattern.matches(REGEX_EMAIL, email)) && (Pattern.matches(REGEX_PASSWORD, password));
    }
    
}