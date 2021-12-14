package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.Algorithm;
import com.DhauEmbunAzzahraJmartPK.Coupon;
import com.DhauEmbunAzzahraJmartPK.Product;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * For basic coupon controller
 * @author Dhau' Embun Azzahra
 */
@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {

    /**
     * Method to get coupon Json Table
     * @return account Json Table
     */
    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

    /**
     * For get request to know if the coupon is already used or not
     * @param id coupon id.
     * @return the condition of coupon. true means used, otherwise false.
     */
    @GetMapping(value = "/{id}/isUsed")
    public boolean isUsed(@PathVariable int id){
        Coupon coupon = Algorithm.<Coupon>find(getJsonTable(),e->e.id == id);
        if (coupon!=null){
            return coupon.isUsed();
        }
        else {
            return true;
        }
    }

    /**
     * For get request to know if the coupon can be applied or not.
     * @param id coupon id.
     * @param price product price.
     * @param discount product discount.
     * @return true if coupon can be applied, false otherwise.
     */
    @GetMapping(value = "/{id}/canApply")
    public boolean canApply(@PathVariable int id,
                            @RequestParam double price,
                            @RequestParam double discount
                            ){
        Coupon coupon = Algorithm.<Coupon>find(getJsonTable(),e->e.id == id);
        if (coupon!=null){
            return coupon.canApply(price, discount);
        }
        else {
            return true;
        }
    }

    /**
     * For get request to get list of coupon which available.
     * @param page page of list
     * @param pageSize page size of list
     * @return list of coupon which available.
     */
    @GetMapping(value = "/getAvailable")
    public List<Coupon> getAvailable(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "2") int pageSize){
        return Algorithm.<Coupon>paginate(getJsonTable(),page,pageSize,e-> !e.isUsed());
    }

    /**
     * The json table of Coupon class.
     * save locally in this path.
     */
    @JsonAutowired(value = Coupon.class, filepath = "C://Proyek Jmart/Jmart/lib/coupon.json")
    public static JsonTable<Coupon> couponTable;
}
