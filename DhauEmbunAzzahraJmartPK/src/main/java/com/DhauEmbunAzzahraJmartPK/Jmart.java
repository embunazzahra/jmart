package com.DhauEmbunAzzahraJmartPK;

import com.DhauEmbunAzzahraJmartPK.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart
{

    public static void main(String[] args) 
    {
        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(()->JsonDBEngine.join()));

    }
}
