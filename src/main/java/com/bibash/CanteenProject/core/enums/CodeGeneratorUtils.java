package com.bibash.CanteenProject.core.enums;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CodeGeneratorUtils {

    public String genOrderCode(){
        Date date = new Date();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char letter = abc.charAt(new Random().nextInt(abc.length()));
        String code = date.getDate() + String.valueOf(new Date().getSeconds()) + date.getMonth() + date.getDay();
        return code;
    }
}
