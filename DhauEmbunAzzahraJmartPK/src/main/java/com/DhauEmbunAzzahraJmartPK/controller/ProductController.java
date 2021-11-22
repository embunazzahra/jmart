package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.Account;
import com.DhauEmbunAzzahraJmartPK.Algorithm;
import com.DhauEmbunAzzahraJmartPK.Product;
import com.DhauEmbunAzzahraJmartPK.ProductCategory;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonAutowired;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {

    public JsonTable<Product> getJsonTable(){
        return productTable;
    }

    @GetMapping(value = "/{id}/store")
    public List<Product> getProductByStore(@PathVariable int id,
                                           @RequestParam int page,
                                           @RequestParam int pageSize){
        return Algorithm.<Product>paginate(getJsonTable(),page,pageSize,(e)->e.accountId == id);
    }

    @PostMapping(value = "/create")
    public Product create(@RequestParam int accountId,
                          @RequestParam String name,
                          @RequestParam int weight,
                          @RequestParam boolean conditionUsed,
                          @RequestParam double price,
                          @RequestParam double discount,
                          @RequestParam ProductCategory category,
                          @RequestParam byte shipmentPlans){
        Account acc = Algorithm.<Account>find(AccountController.accountTable, o->o.id==accountId);
        if(acc==null || acc.store==null)
            return null;
        else {
            Product newProd = new Product(
                    accountId,
                    name,
                    weight,
                    conditionUsed,
                    price,
                    discount,
                    category,
                    shipmentPlans);
            productTable.add(newProd);
            return newProd;
        }
    }

    @GetMapping("/getFiltered")
    public List<Product> getProductFiltered(@RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "2") int pageSize,
                                            @RequestParam int accountId,
                                            @RequestParam String search,
                                            @RequestParam(defaultValue = "0") int minPrice,
                                            @RequestParam(defaultValue = "0") int maxPrice,
                                            @RequestParam ProductCategory category){
        List<Product> filtered;
        if (minPrice!=0 || maxPrice!=0){
            if(minPrice==0.0){
                filtered = productTable.stream()
                        .filter(product -> (product.price<=maxPrice && product.accountId == accountId && product.category.equals(category))).collect(Collectors.toList());
            }
            else if(maxPrice==0.0){
                filtered = productTable.stream()
                        .filter(product -> (product.price>=minPrice && product.accountId == accountId && product.category.equals(category))).collect(Collectors.toList());
            }
            else {
                filtered = productTable.stream()
                        .filter(product -> (product.price<=maxPrice && product.price>=minPrice && product.accountId == accountId && product.category.equals(category))).collect(Collectors.toList());
            }
        }else {
            filtered = productTable.stream()
                    .filter(product -> (product.accountId == accountId && product.category.equals(category))).collect(Collectors.toList());
        }
        return Algorithm.<Product>paginate(filtered,page,pageSize,product -> product.name.toLowerCase().contains(search.toLowerCase()));

    }

    @JsonAutowired(value = Product.class, filepath = "C://Proyek Jmart/Jmart/lib/product.json")
    public static JsonTable<Product> productTable;
}
