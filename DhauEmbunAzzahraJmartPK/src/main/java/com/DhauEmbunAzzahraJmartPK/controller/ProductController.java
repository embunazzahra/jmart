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

/**
 * For product controller
 * @author Dhau' Embun Azzahra
 */
@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
    /**
     * Method to get product Json Table
     * @return product Json Table
     */
    public JsonTable<Product> getJsonTable(){
        return productTable;
    }

    /**
     * For get request to collect product list of a store.
     * @param id account id of the store.
     * @param page page of product list.
     * @param pageSize page size of product list.
     * @return product list of a store.
     */
    @GetMapping(value = "/{id}/store")
    public List<Product> getProductByStore(@PathVariable int id,
                                           @RequestParam int page,
                                           @RequestParam int pageSize){
        return Algorithm.<Product>paginate(getJsonTable(),page,pageSize,(e)->e.accountId == id);
    }

    /**
     * For post request to creates product.
     * @param accountId account id.
     * @param name product name.
     * @param weight product weight.
     * @param conditionUsed product condition used. true if used, false if new.
     * @param price product price.
     * @param discount product discount.
     * @param category product category.
     * @param shipmentPlans shipment plan
     * @return the product that have been made
     */
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

    /**
     * For get request to get all product that have been filtered
     * @param page page of list.
     * @param pageSize page size of list.
     * @param accountId account id.
     * @param search product name.
     * @param minPrice minimum price.
     * @param maxPrice maximum price.
     * @param category product category.
     * @return all product that have been filtered with certain page and page size.
     */
    @GetMapping("/getFiltered")
    public List<Product> getProductFiltered(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "2") int pageSize,
                                            @RequestParam(defaultValue = "-1") int accountId,
                                            @RequestParam String search,
                                            @RequestParam(defaultValue = "0.0") double minPrice,
                                            @RequestParam(defaultValue = "0.0") double maxPrice,
                                            @RequestParam ProductCategory category){
        List<Product> filtered;
        if(accountId<0){
            if (minPrice!=0.0 || maxPrice!=0.0){
                if(minPrice==0.0){
                    filtered = productTable.stream()
                            .filter(product -> (product.price<=maxPrice && product.category.equals(category))).collect(Collectors.toList());
                }
                else if(maxPrice==0.0){
                    filtered = productTable.stream()
                            .filter(product -> (product.price>=minPrice  && product.category.equals(category))).collect(Collectors.toList());
                }
                else {
                    filtered = productTable.stream()
                            .filter(product -> (product.price<=maxPrice && product.price>=minPrice  && product.category.equals(category))).collect(Collectors.toList());
                }
            }else {
                filtered = productTable.stream()
                        .filter(product -> (product.category.equals(category))).collect(Collectors.toList());
            }
        }else {
            if (minPrice!=0.0 || maxPrice!=0.0){
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
        }
        return Algorithm.<Product>paginate(filtered,page,pageSize,product -> product.name.toLowerCase().contains(search.toLowerCase()));

    }

    /**
     * The json table of Product class.
     * save locally in this path.
     */
    @JsonAutowired(value = Product.class, filepath = "C://Proyek Jmart/Jmart/lib/product.json")
    public static JsonTable<Product> productTable;
}
