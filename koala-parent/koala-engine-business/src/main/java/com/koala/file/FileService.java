package com.koala.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ejb.Stateless;

import lombok.extern.log4j.Log4j;

@Log4j
@Stateless
public class FileService {

	private static final String XLS_PROJECT_FILE = "/home/felipeboso/Imagens/about-koala.pdf";

	public InputStream downloadProjectFile() throws FileNotFoundException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(XLS_PROJECT_FILE);
		} catch (FileNotFoundException e) {
			log.info("Error on download file project");
			throw new FileNotFoundException();
		}
		return file;
	}
	
	//TODO REMOVER SOMENTE PARA TESTE
	public InputStream downloadError() throws FileNotFoundException {
		FileInputStream file = null;
		try {
			file = new FileInputStream("a");
		} catch (FileNotFoundException e) {
			log.info("Error on download file project");
			throw new FileNotFoundException();
		}
		return file;
	}
}
