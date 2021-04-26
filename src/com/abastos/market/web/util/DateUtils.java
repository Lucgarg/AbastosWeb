package com.abastos.market.web.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public DateUtils() {
		
	}
	public static String getTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		return new StringBuilder().append(hours).append(":").append(minutes).append(":").append(second).toString();
	}
	public static String getDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int dia = cal.get(Calendar.DAY_OF_MONTH);
	
		return new StringBuilder().append(year).append("-").append(month).append("-").append(dia).toString();
	}
}
