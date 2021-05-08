package se2.hanu_hospital.util;

import java.time.LocalDate;

public class Valid {
    public static boolean stringValid(String string){
        if(string != null){
            return string.length() > 0;
        }
        return false;
    }

    public static boolean unsignedLongValid(Long longNumber){
        if(longNumber != null){
            return longNumber > 0;
        }

        return false;
    }

    public static boolean expiredDateValid(LocalDate expiredDate){
        if(expiredDate != null){
            return expiredDate.isAfter(LocalDate.now());
        }

        return false;
    }
}
