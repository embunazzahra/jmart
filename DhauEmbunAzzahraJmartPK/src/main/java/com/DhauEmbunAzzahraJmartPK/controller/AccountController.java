package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.Account;
import com.DhauEmbunAzzahraJmartPK.Algorithm;
import com.DhauEmbunAzzahraJmartPK.Store;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = null;
    public static final Pattern REGEX_PATTERN_PASSWORD = null;

    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }

    @PostMapping(value = "/login")
    public Account login(@RequestParam String email, @RequestParam String password){
        Account acc = Algorithm.<Account>find(accountTable, (e)->e.email.equals(email));
        if (acc!=null){
            return acc;
        }else {
            return null;
        }
    }

    @PostMapping(value = "/register")
    public Account register(@RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String password){
        if(name.isBlank() || !Pattern.matches(REGEX_EMAIL, email) || !Pattern.matches(REGEX_PASSWORD, password)){
            return null;
        }
        else{
            Account newAcc = new Account(name,email,password,0);
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

        acc.store = new Store(name,address,phoneNumber,0);
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
