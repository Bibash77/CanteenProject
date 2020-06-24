package com.bibash.CanteenProject.core.enums;

import java.security.SecureRandom;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class CodeGeneratorUtils {
    private static final String NUMBER_SEED = "0123456789";
    private static final String COMBO_SEED = "ABCDEFGHIJKLMNOPQRSTUVWXY";
    private static SecureRandom random = new SecureRandom();

    public static String genOrderCode(){
        int length = 3;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(COMBO_SEED.charAt(random.nextInt(COMBO_SEED.length())));
        }
        sb.append(LocalDate.now().getDayOfYear() + random.nextInt(500));
        return sb.toString();
    }
}
