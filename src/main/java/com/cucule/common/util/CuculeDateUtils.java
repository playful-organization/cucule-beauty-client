package com.cucule.common.util;

import com.cucule.common.global.WeekType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 2016/11/06.
 */
public class CuculeDateUtils {
    public static String dateToString(Date date) {
        // e.g 2016年11月13日(日)
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy年MM月dd日(E)").withLocale(Locale.JAPAN);
        DateTime dt = new DateTime(date);
        return dtf.print(dt);
    }

    public static String calcAfterNDateE(int nDate) {
        // e.g 12/22(水)
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd(E)").withLocale(Locale.JAPAN);
        DateTime dt = new DateTime();
        dt = dt.plusDays(nDate);
        return dtf.print(dt);
    }

    public static String calcAfterNDate(int nDate) {
        // e.g 20161222
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMdd").withLocale(Locale.JAPAN);
        DateTime dt = new DateTime();
        dt = dt.plusDays(nDate);
        return dtf.print(dt);
    }

    public static String calcAfterNDateForSql(int nDate) {
        // e.g 2016-12-22
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd").withLocale(Locale.JAPAN);
        DateTime dt = new DateTime();
        dt = dt.plusDays(nDate);
        return dtf.print(dt);
    }

    public static Date parseStringToDate(String fullDateStr) {
        // e.g 2016-12-22
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.JAPAN);
        return dtf.parseDateTime(fullDateStr).toDate();
    }

    public static WeekType calcAfterNWeekType(int nDate) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("E").withLocale(Locale.JAPAN);
        DateTime dt = new DateTime();
        dt = dt.plusDays(nDate);
        WeekType weekType = null;

        switch (dtf.print(dt)) {
            case "日":
                weekType = WeekType.Sun;
                break;
            case "月":
                weekType = WeekType.Mon;
                break;
            case "火":
                weekType = WeekType.Tue;
                break;
            case "水":
                weekType = WeekType.Wed;
                break;
            case "木":
                weekType = WeekType.Thu;
                break;
            case "金":
                weekType = WeekType.Fri;
                break;
            case "土":
                weekType = WeekType.Sat;
                break;
        }
        return weekType;
    }
}
