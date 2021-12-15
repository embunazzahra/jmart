package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.*;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

/**
 *  For phone top up controller
 *
 * @author Dhau' Embun Azzahra
 */
@RestController
@RequestMapping("/phoneTopUp")
public class PhoneTopUpController implements BasicGetController<PhoneTopUp>{
    public static final String REGEX_PHONE = "^(\\+62|62|0)8[1-9][0-9]{6,9}$";
    public static final Pattern REGEX_PATTERN_PHONE = Pattern.compile(REGEX_PHONE);

    /**
     * Method to get phoneTopUp Json Table
     * @return phoneTopUp Json Table
     */
    public JsonTable<PhoneTopUp> getJsonTable(){
        return phoneTopUpTable;
    }



    /**
     * Post request to do phone top up.
     * this will check the buyer id, product id, buyer balance,
     * and phone number validation using regex REGEX_PATTERN_PHONE.
     * @param buyerId buyer id
     * @param productId product id
     * @param phoneNumber phone number
     * @return PhoneTopUp with "FINISHED" status if success,
     * and "FAILED" status if failed.
     */
    @PostMapping(value="/topUpbyPhone")
    public PhoneTopUp phoneTopUp(@RequestParam int buyerId,
                              @RequestParam int productId,
                              @RequestParam String phoneNumber
                              ){
        PhoneTopUp newPhoneTopUp;
        Account account = Algorithm.<Account>find(AccountController.accountTable, e->e.id==buyerId);
        Product product = Algorithm.<Product>find(ProductController.productTable, e->e.id==productId);
        if(REGEX_PATTERN_PHONE.matcher(phoneNumber).matches()
        && account!=null
        && product!=null
        && account.balance>=product.price){
            newPhoneTopUp = new PhoneTopUp(buyerId,productId,phoneNumber, Invoice.Status.FINISHED);
            account.balance-=product.price;
        }else{
            newPhoneTopUp = new PhoneTopUp(buyerId,productId,phoneNumber, Invoice.Status.FAILED);
        }
        phoneTopUpTable.add(newPhoneTopUp);
        return newPhoneTopUp;
    }

    /**
     * The json table of PhoneTopUp class.
     * save locally in this path.
     */
    @JsonAutowired(value = PhoneTopUp.class, filepath = "C://Proyek Jmart/Jmart/lib/phoneTopUp.json")
    public static JsonTable<PhoneTopUp> phoneTopUpTable;
}
