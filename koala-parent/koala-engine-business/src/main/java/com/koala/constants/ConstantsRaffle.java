package com.koala.constants;


public class ConstantsRaffle {

	public static Integer TOTAL_NUMBERS_LOTO_MIN_PLAY = 15;

	public static Integer TOTAL_NUMBERS_LOTO = 25;

	public static String SITE_RESULTS = System.getProperty("caixa.url.loto.resultszip");

	public static String PATH_LOCAL = System.getProperty("file.download.linux");

	public static String FILE_ZIP_NAME = System.getProperty("file.zip.name");

	public static String FILE_HTML_DEFAULT_NAME = System.getProperty("file.html.results.name");

	public static final Integer[] ALL_NUMBERS = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };

	public static final Integer[] PAIR_NUMBERS = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24 };

	public static final Integer[] UNPAIRED_NUMBERS = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25 };

	public static final Integer[] FIRST_DOZEN_NUMBERS = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	public static final Integer[] SECOND_DOZEN_NUMBERS = { 11, 12, 13, 14, 15, 16, 17, 18, 19 };

	public static final Integer[] THIRD_DOZEN_NUMBERS = { 20, 21, 22, 23, 24, 25 };

}
