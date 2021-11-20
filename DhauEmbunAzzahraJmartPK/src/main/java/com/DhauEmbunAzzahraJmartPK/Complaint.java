package com.DhauEmbunAzzahraJmartPK;
import com.DhauEmbunAzzahraJmartPK.dbjson.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Complaint extends Serializable {
    public Date date = new Date();
    public String desc;
    
    public Complaint(String desc){
        this.desc = desc;
    }

    @Override
    public String toString(){
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        return "date="+date_format.format(date)+",desc='"+desc+"'";
    }
}