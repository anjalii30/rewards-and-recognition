/*
package com.rar.utils;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class UtcDate {

    public Date dateToday(LocalDate date) throws ParseException {

//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      //  System.out.println("UTC Time is: " + dateFormat.format(today));

       // return  ResponseEntity.ok(dateFormat.format(date)) ;

        Date dateS = new SimpleDateFormat("yyyy-mm-dd").parse(String.valueOf(date));
        System.out.println(dateS+" simple date format");
        return dateS;
    }


}
*/
