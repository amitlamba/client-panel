package com.und;

import com.und.common.utils.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class ClientPanelApplication2 {


    
    public static void main(String[] args) {
        Test t = new Test();
        Object result = t.m().apply(1);
        System.out.println(result);

        SpringApplication.run(ClientPanelApplication2.class, args);
    }
}
