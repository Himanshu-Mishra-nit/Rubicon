package com.rubicon.water.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonUtils {

    public static Timestamp stringToTimeStamp(String date){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(date));
        return Timestamp.valueOf(localDateTime);
    }

    public static Time stringToTime(String time) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        java.util.Date d1 =(java.util.Date)format.parse(time);
        return new java.sql.Time(d1.getTime());

    }

    public static String duration(Timestamp startDate, Timestamp endDate){
        long time  = endDate.getTime()-startDate.getTime();
        long seconds  = TimeUnit.MILLISECONDS.toSeconds(time);
        long numberOfHours = seconds / 3600 ;
        long numberOfMinutes = (seconds  % 3600 ) / 60;
        long numberOfSeconds = (seconds % 3600 ) % 60  ;
        return numberOfHours+":"+numberOfMinutes+":"+numberOfSeconds;
    }

    public static String convertTimeStamp(Timestamp timeStamp, String outputFormat) throws Exception {
        if (timeStamp == null) {
            return null;
        }
        String outputValue = null;
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
        LocalDateTime localDateTime = timeStamp.toLocalDateTime();
        outputValue = localDateTime.format(outputFormatter);
        return outputValue;
    }

    public static Timestamp generateEndTime(Timestamp startTime, String time) throws Exception {
        if(time.equals(""))
            return startTime;
       String duration[]=time.split(":");

        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        if(!duration[0].equals(""))
        c.add(Calendar.HOUR, Integer.parseInt(duration[0]));
        if(!duration[1].equals(""))
        c.add(Calendar.MINUTE,Integer.parseInt(duration[1]));
        if(!duration[2].equals(""))
        c.add(Calendar.SECOND, Integer.parseInt(duration[2]));

        return new Timestamp(c.getTimeInMillis());

    }

    public static Timestamp getCurrentTime(){
        Date date = new Date();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat formatter = new SimpleDateFormat(pattern);
        return stringToTimeStamp(formatter.format(date));
    }







}
