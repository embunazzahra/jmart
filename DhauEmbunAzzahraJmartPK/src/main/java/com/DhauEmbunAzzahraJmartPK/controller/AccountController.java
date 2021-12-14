package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.Account;
import com.DhauEmbunAzzahraJmartPK.Algorithm;
import com.DhauEmbunAzzahraJmartPK.Store;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.regex.Pattern;

/**
 *  For account controller
 * @author Dhau' Embun Azzahra
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

    /**
     * Method to get account Json Table
     * @return account Json Table
     */
    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }

    /**
     * Post request to make an account logged in
     * after validate the account existance and matching the email and password
     * from the database (Json Table)
     * @param email user's email
     * @param password user's password
     * @return The account which the email and password are matched from
     * the database
     */
    @PostMapping(value = "/login")
    public Account login(@RequestParam String email, @RequestParam String password){
        Account acc = Algorithm.<Account>find(accountTable, (e)->e.email.equals(email));
        String generatedPassword = null;
        try
        {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (acc!=null && acc.password.equals(generatedPassword)){
            return acc;
        }else {
            return null;
        }
    }

    /**
     * Post request for register feature. This will check whether
     * the parameters match the regex and the email is not being registered yet.
     * This will add a new account to account Json Table.
     * @param name account name.
     * @param email account email.
     * @param password account password.
     * @return the account which qualified to register.
     */
    @PostMapping(value = "/register")
    public Account register(@RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String password){
        if(name.isBlank() ||
                !REGEX_PATTERN_EMAIL.matcher(email).matches() ||
                !REGEX_PATTERN_PASSWORD.matcher(password).matches() ||
                Algorithm.<Account>exists(accountTable, e->e.email.equals(email))){
            return null;
        }
        else{
            String generatedPassword = null;
            try
            {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");

                // Add password bytes to digest
                md.update(password.getBytes());

                // Get the hash's bytes
                byte[] bytes = md.digest();

                // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }

                // Get complete hashed password in hex format
                generatedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Account newAcc = new Account(name,email,generatedPassword,0);
            accountTable.add(newAcc);
            return newAcc;
        }
    }


    /**
     * Post request to register store of an account.
     * @param id account id
     * @param name store name
     * @param address store address
     * @param phoneNumber store phone number
     * @return the store being registered
     */
    @PostMapping(value = "/{id}/registerStore")
    public Store registerStore(@PathVariable int id,
                               @RequestParam String name,
                               @RequestParam String address,
                               @RequestParam String phoneNumber){
        Account acc = Algorithm.<Account>find(accountTable, o->o.id==id);
        if(acc==null||acc.store!=null)
            return null;
        acc.store = new Store(name,address,phoneNumber);
            return acc.store;
    }

    /**
     * Post request to top up account balance
     * @param id account id
     * @param balance the amount balance to top up
     * @return if success return true, otherwise false
     */
    @PostMapping(value = "/{id}/topUp")
    public boolean topUp(@PathVariable int id, @RequestParam double balance){
        Account acc = Algorithm.<Account>find(accountTable, o->o.id==id);
        if (acc == null){
            return false;
        }else {
            acc.balance+=balance;
            return true;
        }
    }

    /**
     * The json table of Account class.
     * save locally in this path.
     */
    @JsonAutowired(value = Account.class, filepath = "C://Proyek Jmart/Jmart/lib/account.json")
    public static JsonTable<Account> accountTable;

}
