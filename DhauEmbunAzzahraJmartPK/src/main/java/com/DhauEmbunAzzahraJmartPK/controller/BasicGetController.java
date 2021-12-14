package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.Algorithm;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import com.DhauEmbunAzzahraJmartPK.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * For basic get controller
 * @author Dhau' Embun Azzahra
 */
@RestController
public interface BasicGetController<T extends Serializable> {

    /**
     * get the page in certain page size of json table.
     * @param page the page.
     * @param pageSize the page size.
     * @return list of all item in the page.
     */
    @GetMapping(path = "/page")
    default List<T> getPage(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "pageSize", defaultValue = "2") int pageSize){
        return Algorithm.<T>paginate(getJsonTable(),page,pageSize,(e)->true);
    }
    JsonTable<T> getJsonTable();

    /**
     * get the object with certain id
     * @param id the id
     * @return the object with certain id
     */
    @GetMapping(path = "/{id}")
    default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(),(e)->e.id==id);
    }
}
