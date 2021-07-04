package com.sky.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public  static String getNowTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = format.format(date);
        return datetime;
    }
}
