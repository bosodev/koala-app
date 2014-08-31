package com.koala.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.Stateless;

import lombok.AllArgsConstructor;

import com.koala.constants.ConstantsRaffle;

@Stateless
@AllArgsConstructor
public class FileLotoService {

	public void downloadAndUnzip() {
		this.downloadFileZip();
		UnZipFileService unzip = new UnZipFileService();
		unzip.unZipIt();
	}

	public File downloadFileZip() {
		if (!checkFileExists()) {
			try {
				CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
				URL urlSiteCaixa = new URL(ConstantsRaffle.SITE_RESULTS);
				InputStream stream = urlSiteCaixa.openStream();
				FileOutputStream fos = new FileOutputStream(ConstantsRaffle.PATH_LOCAL + File.separator + ConstantsRaffle.FILE_ZIP_NAME);
				readFileWeb(stream, fos);
				return new File(ConstantsRaffle.PATH_LOCAL + File.separator + ConstantsRaffle.FILE_ZIP_NAME);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void readFileWeb(InputStream stream, FileOutputStream fos) throws IOException {
		int bytes = 0;
		while ((bytes = stream.read()) != -1) {
			fos.write(bytes);
		}
		stream.close();
		fos.close();
	}

	public boolean checkFileExists() {
		File file = new File(ConstantsRaffle.PATH_LOCAL + File.separator + ConstantsRaffle.FILE_ZIP_NAME);
		return file.exists();
	}
}
