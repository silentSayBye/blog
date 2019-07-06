package com.destiny.blog.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName DateUtils
 * @Author Administrator
 * @Date 2019/7/220:27
 * @Version 1.0
 **/
public class DateUtils {

    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE = "yyyy-MM-dd";

    public static  final String TIME = "HH:mm:ss";

    public static FastDateFormat fds = FastDateFormat.getInstance(DATETIME, Locale.CHINA);

    public static String format(Date date){
        return fds.format(date);
    }

    public static String format(long millis){
        return fds.format(millis);
    }

   public static String format(Calendar calendar){
        return fds.format(calendar);
   }
}
