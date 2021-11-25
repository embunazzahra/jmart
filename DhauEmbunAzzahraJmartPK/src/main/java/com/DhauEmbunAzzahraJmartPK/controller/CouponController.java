package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.Algorithm;
import com.DhauEmbunAzzahraJmartPK.Coupon;
import com.DhauEmbunAzzahraJmartPK.Product;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {

    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

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

    @GetMapping(value = "/getAvailable")
    public List<Coupon> getAvailable(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "2") int pageSize){
        return Algorithm.<Coupon>paginate(getJsonTable(),page,pageSize,e-> !e.isUsed());
    }

    @JsonAutowired(value = Coupon.class, filepath = "C://Proyek Jmart/Jmart/lib/coupon.json")
    public static JsonTable<Coupon> couponTable;
}
