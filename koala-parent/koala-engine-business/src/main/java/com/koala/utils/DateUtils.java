package com.koala.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date formatRaffleDate(String date) throws ParseException {
		final DateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date formatedDate = dformat.parse(date);
		return formatedDate;
	}
}
