package com.DhauEmbunAzzahraJmartPK.controller;

import com.DhauEmbunAzzahraJmartPK.Algorithm;
import com.DhauEmbunAzzahraJmartPK.dbjson.JsonTable;
import com.DhauEmbunAzzahraJmartPK.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface BasicGetController<T extends Serializable> {
    @GetMapping(path = "/page")
    default List<T> getPage(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "pageSize", defaultValue = "2") int pageSize){
        return Algorithm.<T>paginate(getJsonTable(),page,pageSize,(e)->true);
    }
    JsonTable<T> getJsonTable();
    @GetMapping(path = "/{id}")
    default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(),(e)->e.id==id);
    }
}
