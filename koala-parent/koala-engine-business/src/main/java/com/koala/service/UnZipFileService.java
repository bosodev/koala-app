package com.koala.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import lombok.extern.log4j.Log4j;

import com.koala.constants.ConstantsRaffle;

@Log4j
public class UnZipFileService {
	List<String> fileList;

	public void unZipIt() {
		String zipFile = getZipLocation();
		byte[] buffer = new byte[1024];
		try {
			File folder = new File(ConstantsRaffle.PATH_LOCAL);
			if (!folder.exists())
				folder.mkdir();
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry ze = zis.getNextEntry();
			ze = readZip(buffer, zis, ze);
			zis.closeEntry();
			zis.close();
			log.info("Unzipped file to importation done!!!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private ZipEntry readZip(byte[] buffer, ZipInputStream zis, ZipEntry ze) throws FileNotFoundException, IOException {
		while (ze != null) {
			String fileName = ze.getName();
			File newFile = new File(ConstantsRaffle.PATH_LOCAL + File.separator + fileName);
			new File(newFile.getParent()).mkdirs();
			FileOutputStream fos = new FileOutputStream(newFile);
			readBuffer(buffer, zis, fos);
			fos.close();
			ze = zis.getNextEntry();
		}
		return ze;
	}

	private void readBuffer(byte[] buffer, ZipInputStream zis, FileOutputStream fos) throws IOException {
		int len;
		while ((len = zis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
	}

	private String getZipLocation() {
		String zipFile = ConstantsRaffle.PATH_LOCAL + File.separator + ConstantsRaffle.FILE_ZIP_NAME;
		return zipFile;
	}
}