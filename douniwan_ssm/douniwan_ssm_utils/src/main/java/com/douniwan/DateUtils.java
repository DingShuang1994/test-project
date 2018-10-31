package com.douniwan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转换成字符串
     * @param date  将要转换的日期
     * @param ptt 要转换成的格式
     * @return 日期数据类型转换成指定类型字符串后的数据
     */
    public static String dateToString(Date date , String ptt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ptt);
         return simpleDateFormat.format(date);
    }

    /**
     *
     * @param string 要转换的字符串
     * @param ptt 转换的格式
     * @return  日期类型
     */
    public static Date stringToDate(String string , String ptt) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ptt);
         return  simpleDateFormat.parse(string);
    }
}
