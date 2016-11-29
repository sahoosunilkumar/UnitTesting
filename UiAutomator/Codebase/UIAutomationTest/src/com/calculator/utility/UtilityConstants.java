package com.calculator.utility;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilityConstants {
public static final String TEST_NAME = "Test Contact$";
public static final String TEST_EMAIL = "testmail$@testmail.com";
public static final String TEST_MOBILE = "895162$";
public static final String REPLACE_CHAR = "$";

/**
 * Get unique variable
 * @return
 */
public static int getUniqueVariable(){
	Date currentDate = new Date();
	Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
	calendar.setTime(currentDate);   // assigns calendar to given date 
	return calendar.get(Calendar.HOUR_OF_DAY)*60 + calendar.get(Calendar.MINUTE)*60 + calendar.get(Calendar.SECOND);
}

}
