package com.day_28.station.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 */
public class DateUtil {
    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static String DateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String format = dateFormat.format(date);
        return format;

    }

    /**
     * 字符串转换成日期格式
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date parse = null;
        try {
            parse = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;

    }

    /**
     * 日期加减运算
     * @param date  对指定日期运算
     * @param calendarField  对日期中的那个类型进行运算,比如1表示对年运算, 5表示对天进行运算
     * @param amount 正数表示加,负数表示减
     * @return
     */
    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }



}
