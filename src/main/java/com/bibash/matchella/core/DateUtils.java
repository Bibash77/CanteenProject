package com.bibash.matchella.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.bibash.matchella.api.Wallet.Wallet;
import com.bibash.matchella.core.enums.TransactionType;

@Component
public class DateUtils {

    public Date getCurrentDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
       return date;
    }

    public String transactionMessage(Wallet wallet, TransactionType transactionType){
        if(transactionType.equals(TransactionType.TOPUP)){
            return wallet.getDepositAmount().toString() + " has been Top-Up on" + " " + wallet.getUser().getUsername() + " Acoount";
        }else {
     /*       return wallet.getUser().getUsername() + " ordered" + " " + itemName + "." + amount.toString() +  "has been detucted.";
      */ return "no messaage";  }
    }
}
