package com.jeecms.cms.json;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类.
 * 
 * @author zhanghai
 * @version v1.0 2012-8-20
 */
public class DateFormat {
    /**
     * SimpleDateFormat.
     */
    private static SimpleDateFormat formatter;

    /**
     * .
     */
    public DateFormat() {
    }

    /**
     * .
     * 
     * @param aDate
     *            {@link Date}
     * @return {@link String}
     */
    public static String shortDate(Date aDate) {
        if (aDate == null) {
            return "";
        }
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(aDate);
    }

    /**
     * .
     * 
     * @param aDate
     *            {@link Date}
     * @return {@link String}
     */
    public static String shortDateNotag(Date aDate) {
        if (aDate == null) {
            return "";
        }
        formatter = new SimpleDateFormat("yyMMdd");
        return formatter.format(aDate);
    }

    /**
     * .
     * 
     * @param aDate
     *            {@link Date}
     * @return {@link String}
     */
    public static String yearMonth(Date aDate) {
        if (aDate == null) {
            return "";
        }
        formatter = new SimpleDateFormat("yyyyMM");
        return formatter.format(aDate);
    }

    /**
     * .
     * 
     * @param aDate
     *            {@link Date}
     * @return {@link String}
     */
    public static String mailDate(Date aDate) {
        formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return formatter.format(aDate);
    }

    /**
     * .
     * 
     * @param aDate
     *            {@link Date}
     * @return {@link String}
     */
    public static String longDate(Date aDate) {
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(aDate);
    }

    /**
     * .
     * 
     * @param aDate
     *            {@link Date}
     * @return {@link String}
     */
    public static String shortDateGB(Date aDate) {
        formatter = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
        return formatter.format(aDate);
    }

    /**
     * .
     * 
     * @param aDate
     *            {@link Date}
     * @return {@link String}
     */
    public static String longDateGB(Date aDate) {
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(aDate);
    }

    /**
     * .
     * 
     * @param aDate
     *            {@link Date}
     * @param formatStr
     *            {@link String}
     * @return {@link String}
     */
    public static String formatDate(Date aDate, String formatStr) {
        formatter = new SimpleDateFormat(formatStr);
        return formatter.format(aDate);

    }

    /**
     * .
     * 
     * @param aDate
     *            {@link Date}
     * @return {@link String}
     */
    public static String LDAPDate(Date aDate) {
        return formatDate(aDate, "yyyyMMddHHmm'Z'");
    }

    /**
     * .
     * 
     * @param yyyymmdd
     *            {@link String}
     * @return {@link Date}
     */
    public static Date getDate(String yyyymmdd) {
        if ((null == yyyymmdd) || (yyyymmdd.trim().length() == 0)) {
            return null;
        }
        int year = Integer.parseInt(yyyymmdd.substring(0, yyyymmdd.indexOf("-")));
        int month = Integer.parseInt(yyyymmdd.substring(yyyymmdd.indexOf("-") + 1, yyyymmdd.lastIndexOf("-")));
        int day = Integer.parseInt(yyyymmdd.substring(yyyymmdd.lastIndexOf("-") + 1));
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.getTime();

    }

    /**
     * .
     * 
     * @param yyyymmdd
     *            {@link String}
     * @return {@link Date}
     */
    public static Date getDateFull(String yyyymmdd) {
        if ((null == yyyymmdd) || (yyyymmdd.trim().length() == 0)) {
            return null;
        }
        int year = Integer.parseInt(yyyymmdd.substring(0, yyyymmdd.indexOf("-")));
        int month = Integer.parseInt(yyyymmdd.substring(yyyymmdd.indexOf("-") + 1, yyyymmdd.lastIndexOf("-")));
        int day = Integer.parseInt(yyyymmdd.substring(yyyymmdd.lastIndexOf("-") + 1));
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 24, 60, 60);
        return cal.getTime();

    }

    /**
     * .
     * 
     * @param strDate
     *            {@link String}
     * @return {@link Date}
     */
    public static Date parser(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * .
     * 
     * @param strDate
     *            {@link String}
     * @param formatter
     *            {@link String}
     * @return {@link Date}
     */
    public static Date parser(String strDate, String formatter) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get Date with only date num. baoxy add.
     * 
     * @param yyyymmdd
     *            {@link String}
     * @return {@link Date}
     */
    public static Date getDateOnly(String yyyymmdd) {
        if ((null == yyyymmdd) || (yyyymmdd.trim().length() == 0)) {
            return null;
        }
        int year = Integer.parseInt(yyyymmdd.substring(0, yyyymmdd.indexOf("-")));
        int month = Integer.parseInt(yyyymmdd.substring(yyyymmdd.indexOf("-") + 1, yyyymmdd.lastIndexOf("-")));
        int day = Integer.parseInt(yyyymmdd.substring(yyyymmdd.lastIndexOf("-") + 1));
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * .
     * 
     * @param myDate
     *            {@link Date}
     * @param amount
     *            int
     * @return {@link Date}
     */
    public static Date addMonth(Date myDate, int amount) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(myDate);
        boolean isEndDayOfMonth_old = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == cal
                .get(GregorianCalendar.DAY_OF_MONTH);
        cal.add(GregorianCalendar.MONTH, amount);
        boolean isEndDayOfMonth_new = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == cal
                .get(GregorianCalendar.DAY_OF_MONTH);
        if (isEndDayOfMonth_old && !isEndDayOfMonth_new) {
            cal.set(GregorianCalendar.DATE, cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        }
        return cal.getTime();
    }

    /**
     * .
     * 
     * @param myDate
     *            {@link Date}
     * @param amount
     *            {@link Integer}
     * @return {@link Date}
     */
    public static Date addDay(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DAY_OF_MONTH, amount);
        return cal.getTime();
    }

    /**
     * .
     * 
     * @param myDate
     *            {@link Date}
     * @param amount
     *            {@link Integer}
     * @return {@link Date}
     */
    public static Date addMinute(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        int minute = 0;
        int amountv = -(amount);

        if (amountv > 60) {

            int hour = (int) amountv / 60;

            if (hour * 60 > amountv) {
                minute = hour * 60 - amountv;

                cal.add(Calendar.HOUR_OF_DAY, -hour);
                cal.add(Calendar.MINUTE, minute);

            } else if (hour * 60 < amountv) {

                minute = amountv - hour * 60;
                cal.add(Calendar.HOUR_OF_DAY, -hour);
                cal.add(Calendar.MINUTE, -minute);

            } else {
                cal.add(Calendar.HOUR_OF_DAY, -hour);
            }

        } else {

            cal.add(Calendar.MINUTE, -amountv);
        }
        return cal.getTime();
    }

    /**
     * .
     * 
     * @param myDate
     *            {@link Date}
     * @param amount
     *            {@link Integer}
     * @return {@link Date}
     */
    public static Date addYear(Date myDate, int amount) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(myDate);
        boolean isEndDayOfMonth_old = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == cal
                .get(GregorianCalendar.DAY_OF_MONTH);
        cal.add(GregorianCalendar.YEAR, amount);
        boolean isEndDayOfMonth_new = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == cal
                .get(GregorianCalendar.DAY_OF_MONTH);
        if (isEndDayOfMonth_old && !isEndDayOfMonth_new) {
            cal.set(GregorianCalendar.DATE, cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        }
        return cal.getTime();
    }

    /**
     * .
     * 
     * @param myDate
     *            {@link Date}
     * @return int
     */
    public static int getWeekDay(Date myDate) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(myDate);
        return cal.get(GregorianCalendar.DAY_OF_WEEK);
    }

    /**
     * .
     * 
     * @param myDate
     *            {@link Date}
     * @return {@link Integer}
     */
    public static int getConvertWeekDay(Date myDate) {
        int day = getWeekDay(myDate);
        int result = day - 1;
        if (result == 0) {
            result = 7;
        }
        return result;
    }

    /**
     * .
     * 
     * @param myDate
     *            {@link Date}
     * @return {@link Integer}
     */
    public static int getTimeFromDate(Date myDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
        int result = Integer.parseInt(sdf.format(myDate));
        return result;
    }

    /**
     * .
     * 
     * @param startDate
     *            {@link Date}
     * @param endDate
     *            {@link Date}
     * @return {@link Long}
     */
    @SuppressWarnings("unused")
    public static long getDaysBetweenDate(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        Date startDateTemp = null;
        startDateTemp = startDate;
        cal.setTime(startDate);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        startDateTemp = cal.getTime();
        cal.setTime(endDate);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTime().getTime() - startDate.getTime()) / 86400000;

    }

    /**
     * wanggenyou.
     * 日期格式转换 eg:二〇一〇年五月七日
     * 
     * @param date
     *            {@link Date}
     * @return {@link String}
     */
    public static String DateToCN(Date date) {
        if (null == date || "".equals(date)) {
            return null;
        }
        String[] CN = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        StringBuffer cn = new StringBuffer();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        for (int i = 0; i < year.length(); i++) {
            cn.append(CN[year.charAt(i) - 48]);
        }
        cn.append("年");
        int mon = calendar.get(Calendar.MONTH) + 1;
        if (mon < 10) {
            cn.append(CN[mon]);
        } else if (mon < 20) {
            if (mon == 10) {
                cn.append("十");
            } else {
                cn.append("十").append(CN[mon % 10]);
            }
        }
        cn.append("月");
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            cn.append(CN[day]);
        } else if (day < 20) {
            if (day == 10) {
                cn.append("十");
            } else {
                cn.append("十").append(CN[day % 10]);
            }
        } else if (day < 30) {
            if (day == 20) {
                cn.append("二十");
            } else {
                cn.append("二十").append(CN[day % 10]);
            }
        } else {
            cn.append("三十").append(CN[day % 10]);
        }
        cn.append("日");
        return cn.toString();
    }

    /**
     * 获取当前日期是星期几.
     * 
     * @param dt
     *            {@link Date}
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * .
     * 
     * @param date
     *            {@link Date}
     * @param hm
     *            {@link Long}
     * @return {@link Date}
     */
    public static Date lastDate(Date date, Long hm) {
        try {
            date.setTime(date.getTime() + hm);
            return date;
        } catch (Exception e) {
            return null;
        }
    }
    /*
        public static void main(String[] args) {
        }*/
}