package com.talkingdata.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关的转换
 * User：    ysl
 * Date:   2016/7/13
 * Time:   15:49
 */
public class DateUtils {

    public static void main(String[] args)throws Exception
    {
        System.out.println(dateToMillions(strToDate("2057-08-10 07:59:59")));
        System.out.println(millionsToStrDate(1502323199999l));
        System.out.println(System.currentTimeMillis());
        System.out.println(getBeforeSecond(DateUtils.currentMillions(),20));
    }
    /**
     * 将字符串转换为日期类型
     * @param strTime       :日期类型字符串如2008-07-10 19:20:00
     * @return                  ：返回一个日期
     */
    public static Date strToDate(String strTime)throws Exception
    {
        Date date = null;
            if(strTime!=null&&strTime.length()==19)
            {
                SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                date  = sdf.parse(strTime );
            }else if(strTime!=null&&strTime.length()==10)
            {
                SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd" );
                date = sdf.parse(strTime );
            }else{
                return null;
            }

        return  date;
    }

    /**
     * 将日期类型转换为其毫秒数
     * @param date      ：日期类型
     * @return
     */
    public static long dateToMillions(Date date)
    {
        if (date!=null)
        {
            return date.getTime();
        }else
        {
            return  0l;
        }
    }

    /**
     * 当前时间的毫秒数
     * @return
     */
    public static long currentMillions()
    {
        return System.currentTimeMillis();
    }

    /**
     * 将当前日期转为长字符串
     * @param date      :日期
     * @return          ：日期字符串
     */
    public static String dateToLongStr(Date date)
    {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        if(date!=null)
        {
            return sdf.format(date);
        }else{
            return null;
        }
    }

    /**
     * 将当前日期转为短字符串
     * @param date      :日期
     * @return          ：日期字符串
     */
    public static String dateToShortStr(Date date)
    {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        if(date!=null)
        {
            return sdf.format(date);
        }else{
            return null;
        }
    }

    /**
     * 将当前日期转为短字符串,带中文的
     * @param date      :日期
     * @return          ：日期字符串
     */
    public static String dateToZhShortStr(Date date)
    {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy年MM月dd日" );
        if(date!=null)
        {
            return sdf.format(date);
        }else{
            return null;
        }
    }
    /**
     * 求取两个时间之间的天数
     *
     * @param startDate1
     * @param endDate2
     * @return
     */
    public static long getDays(String startDate1, String endDate2) throws Exception
    {
        if (startDate1 == null || startDate1.equals(""))
            return 0;
        if (endDate2 == null || endDate2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        date = myFormatter.parse(endDate2);
        mydate = myFormatter.parse(startDate1);
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     *从当前时间算起，延迟或者向前推delaySecond秒
     * @param nowdateMillions          :当前的时间戳
     * @param delaySecond               ：延迟或者向前推的秒数
     * @return
     */
    public static long getBeforeSecond(long  nowdateMillions, int delaySecond)throws Exception
    {
        long  timeMillions = 0l;
           // Date date = strToDate(nowdate);
            Calendar calendar = Calendar.getInstance();
            //calendar.setTime(date);
            calendar.setTimeInMillis(nowdateMillions);
            calendar.add(Calendar.SECOND, -delaySecond);
            Date date = calendar.getTime();
            timeMillions = date.getTime();
        return  timeMillions;
    }


    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    public static long getBeforeDay(String nowdate, int delay)throws Exception
    {
        long  timeMillions = 0l;
            Date date = strToDate(nowdate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -delay);
            date = calendar.getTime();
            timeMillions = date.getTime();
        return  timeMillions;
    }


    /**
     * 将时间的毫秒数转换为日期字符串
     * @param timeLong      ：时间的毫秒数
     * @return
     */
    public static String millionsToStrDate(long timeLong)
    {
        if(timeLong>0)
        {
            Date date = new Date(timeLong);
            return dateToLongStr(date);
        }else
        {
            return null;
        }
    }

    /**
     * 将时间的毫秒数转换为日期字符串,带中文的
     * @param timeLong      ：时间的毫秒数
     * @return
     */
    public static String millionsToStrZhDate(long timeLong)
    {
        if(timeLong>0)
        {
            Date date = new Date(timeLong);
            return dateToZhShortStr(date);
        }else
        {
            return null;
        }
    }
}
