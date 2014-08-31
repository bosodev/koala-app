package com.koala.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.koala.constants.ConstantsRaffle;
import com.koala.entity.Raffle;
import com.koala.utils.DateUtils;

@Stateless
public class LotoImportService {

	private static final String EMPTY = "";

	private static final String HTML_TD = "<td>";

	private static final String HTML_TD_CLOSE = "</td>";

	private static final String HTML_TR_CLOSE = "</tr>";

	@EJB
	private FileLotoService fileLotoService;

	private File getFileHTMLFromPath() throws FileNotFoundException {
		fileLotoService.downloadAndUnzip();
		File fileHtml = new File(ConstantsRaffle.PATH_LOCAL + File.separator + ConstantsRaffle.FILE_HTML_DEFAULT_NAME);
		if (fileHtml.exists())
			return fileHtml;
		else
			throw new FileNotFoundException();
	}

	public Collection<Raffle> readHtmlFile() throws IOException, ParseException {
		File file = getFileHTMLFromPath();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		List<String> data = new ArrayList<String>();
		List<Raffle> dataObject = new ArrayList<Raffle>();
		int trRead = 0;
		DateUtils dateUtils = new DateUtils();
		trRead = readLine(br, data, dataObject, trRead, dateUtils);
		br.close();
		fr.close();
		return dataObject;
	}

	private int readLine(BufferedReader br, List<String> data, List<Raffle> dataObject, int trRead, DateUtils dateUtils) throws IOException, ParseException {
		while (br.ready()) {
			String linha = br.readLine();
			if (linha.contains(HTML_TD))
				data.add(linha.replace(HTML_TD, EMPTY).replace(HTML_TD_CLOSE, EMPTY));
			trRead = findRaffleData(data, dataObject, trRead, dateUtils, linha);
		}
		return trRead;
	}

	private int findRaffleData(List<String> data, List<Raffle> dataObject, int trRead, DateUtils dateUtils, String linha) throws ParseException {
		if (linha.contains(HTML_TR_CLOSE)) {
			trRead++;
			if (trRead != 1) {
				dataObject.add(newDataObject(data, dateUtils));
				data.clear();
			}
		}
		return trRead;
	}

	public Raffle newDataObject(List<String> dataFile, DateUtils dateUtils) throws ParseException {
		Raffle object = new Raffle();
		if (!dataFile.isEmpty()) {
			object.setConcurse(Integer.parseInt(dataFile.get(0)));
			object.setDate(DateUtils.formatRaffleDate(dataFile.get(1)));
			List<Integer> numbersRaffle = getOrderNumbers(dataFile);
			object.setBall1(numbersRaffle.get(0));
			object.setBall2(numbersRaffle.get(1));
			object.setBall3(numbersRaffle.get(2));
			object.setBall4(numbersRaffle.get(3));
			object.setBall5(numbersRaffle.get(4));
			object.setBall6(numbersRaffle.get(5));
			object.setBall7(numbersRaffle.get(6));
			object.setBall8(numbersRaffle.get(7));
			object.setBall9(numbersRaffle.get(8));
			object.setBall10(numbersRaffle.get(9));
			object.setBall11(numbersRaffle.get(10));
			object.setBall12(numbersRaffle.get(11));
			object.setBall13(numbersRaffle.get(12));
			object.setBall14(numbersRaffle.get(13));
			object.setBall15(numbersRaffle.get(14));
		}
		return object;
	}

	private List<Integer> getOrderNumbers(List<String> dataFile) {
		Set<Integer> numbers = new TreeSet<Integer>();
		numbers.add(Integer.parseInt(dataFile.get(2)));
		numbers.add(Integer.parseInt(dataFile.get(3)));
		numbers.add(Integer.parseInt(dataFile.get(4)));
		numbers.add(Integer.parseInt(dataFile.get(5)));
		numbers.add(Integer.parseInt(dataFile.get(6)));
		numbers.add(Integer.parseInt(dataFile.get(7)));
		numbers.add(Integer.parseInt(dataFile.get(8)));
		numbers.add(Integer.parseInt(dataFile.get(9)));
		numbers.add(Integer.parseInt(dataFile.get(10)));
		numbers.add(Integer.parseInt(dataFile.get(11)));
		numbers.add(Integer.parseInt(dataFile.get(12)));
		numbers.add(Integer.parseInt(dataFile.get(13)));
		numbers.add(Integer.parseInt(dataFile.get(14)));
		numbers.add(Integer.parseInt(dataFile.get(15)));
		numbers.add(Integer.parseInt(dataFile.get(16)));
		List<Integer> numbersRaffle = new ArrayList<Integer>();
		numbersRaffle.addAll(numbers);
		return numbersRaffle;
	}

}