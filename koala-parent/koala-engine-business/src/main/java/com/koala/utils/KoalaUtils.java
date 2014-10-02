package com.koala.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.koala.entity.raffle.Raffle;

public class KoalaUtils {

	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String SET_BALL = "setBall";
	private static final String GET_BALL = "getBall";

	public static Date formatRaffleDate(String date) throws ParseException {
		final DateFormat dformat = new SimpleDateFormat(DATE_FORMAT);
		java.util.Date formatedDate = dformat.parse(date);
		return formatedDate;
	}

	public static String clearData(String data) {
		int index = data.indexOf(">");
		return data.substring(index + 1).replaceAll("</td>", "");
	}

	public static Raffle populateRaffleNumbers(int value, int sequence) throws Exception {
		Raffle raffle = new Raffle();
		invokeSetMethods(raffle, value, sequence);
		return raffle;
	}

	public static Raffle populateRaffleNumbers(Raffle raffle, int value, int sequence) throws Exception {
		invokeSetMethods(raffle, value, sequence);
		return raffle;
	}

	public static Raffle populateRaffleNumbers(List<Integer> values) throws Exception {
		Raffle raffle = new Raffle();
		for (int i = 0; i < values.size(); i++)
			invokeSetMethods(raffle, values.get(i), i);
		return raffle;
	}
	
	public static List<Integer> asListRaffle(Raffle raffle) throws Exception {
		List<Integer> values = new ArrayList<Integer>();
		for (int i = 1; i <= 15; i++)
			values.add(invokeGetMethods(raffle, i));
		return values;
	}

	private static Integer invokeGetMethods(Raffle raffle,int sequence) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method method = raffle.getClass().getMethod(GET_BALL + sequence);
		return (Integer) method.invoke(raffle);
	}
	
	private static void invokeSetMethods(Raffle raffle, int value, int sequence) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		sequence++;
		Method method = raffle.getClass().getMethod(SET_BALL + sequence, new Class[] { Integer.class });
		method.invoke(raffle, value);
	}

}
