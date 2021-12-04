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

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }

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




    @JsonAutowired(value = Account.class, filepath = "C://Proyek Jmart/Jmart/lib/account.json")
    public static JsonTable<Account> accountTable;

    /*@GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        return new Account(name, email, password, 0);
    }

    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }*/
}
