package com.koala.service;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.koala.constants.ConstantsRaffle;
import com.koala.entity.Raffle;
import com.koala.utils.DateUtils;

@Stateless
public class ImportService {
	private static final String JSON = "json";
	private static final String ENABLE_CONCURSE = "&concurso=";
	private static final String SET_BALL = "setBall";
	private static final String REGEX = "\\|";
	private static final String DOZENS = "dezenas";
	private static final String DATE = "data";
	private static final String NUMBER = "numero";
	private static final String TAG_CONCURSE = "concurso";

	public Raffle getData(String format) throws Exception {
		return getData(format, null);
	}

	public Raffle getData(String format, Integer concurse) throws Exception {
		String urlFormatted = getURLFormatted(format, concurse);
		if (format.equalsIgnoreCase(JSON))
			return getJsonData(urlFormatted);
		else
			return getXMLData(urlFormatted);
	}

	private Raffle getXMLData(String urlFormatted) throws Exception {
		URL url = new URL(urlFormatted);
		URLConnection conn = url.openConnection();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(conn.getInputStream());
		return readXML(doc);
	}

	private Raffle readXML(Document doc) throws Exception {
		for (int i = 0; i < doc.getDocumentElement().getChildNodes().getLength(); i++) {
			Node node = doc.getDocumentElement().getChildNodes().item(i);
			if (TAG_CONCURSE.equals(node.getNodeName()))
				return readConcurseTag(node);
		}
		return null;
	}

	private Raffle readConcurseTag(Node node) throws DOMException, Exception {
		Raffle raffle = new Raffle();
		for (int j = 0; j < node.getChildNodes().getLength(); j++) {
			Node nodeConcurse = node.getChildNodes().item(j);
			setDataRaffle(raffle, nodeConcurse);
		}
		return raffle;
	}

	private void setDataRaffle(Raffle raffle, Node nodeConcurse) throws ParseException, Exception {
		String nodeValue = null;
		if (nodeConcurse.getFirstChild() != null)
			nodeValue = nodeConcurse.getFirstChild().getNodeValue();
		if (NUMBER.equals(nodeConcurse.getNodeName()))
			setConcurse(raffle, nodeValue);
		else if (DATE.equals(nodeConcurse.getNodeName()))
			setDate(raffle, nodeValue);
		else if (DOZENS.equals(nodeConcurse.getNodeName()))
			setDozens(raffle, nodeValue);
	}

	private void setDate(Raffle raffle, String data) throws ParseException {
		raffle.setDate(DateUtils.formatRaffleDate(data));
	}

	private void setConcurse(Raffle raffle, String data) {
		raffle.setConcurse(Integer.valueOf(data));
	}

	private String getURLFormatted(String format, Integer concurse) {
		if (concurse != null)
			return String.format(ConstantsRaffle.API_LOTOFACIL_URL + ENABLE_CONCURSE, format) + concurse;
		return String.format(ConstantsRaffle.API_LOTOFACIL_URL, format);
	}

	public Raffle setDozens(Raffle raffle, String data) throws Exception {
		String[] numbers = data.split(REGEX);
		for (int i = 0; i < numbers.length; i++)
			setRaffleData(raffle, Integer.valueOf(numbers[i]), i + 1);
		return raffle;
	}

	private void setRaffleData(Raffle raffle, int value, int sequence) throws Exception {
		Method method = raffle.getClass().getMethod(SET_BALL + sequence, new Class[] { Integer.class });
		method.invoke(raffle, value);
	}

	// TODO NOT IMPLEMENT YET
	private Raffle getJsonData(String url) {
		return null;
	}
}