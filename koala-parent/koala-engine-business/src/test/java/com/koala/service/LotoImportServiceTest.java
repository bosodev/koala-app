package com.koala.service;

import static java.io.File.separator;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.koala.entity.Raffle;

public class LotoImportServiceTest {

	private static final String FILE_PATH = "src/test/resources";

	private LotoImportService lotoImportService;

	@Mock
	private FileLotoService fileLotoService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		lotoImportService = spy(new LotoImportService(fileLotoService));
	}

	@Test
	public void testLoadData() throws Exception {
		doReturn(new File(FILE_PATH + separator + "simpleResult.html")).when(lotoImportService).getFileHTMLFromPath();
		List<Raffle> raffles = lotoImportService.readHtmlFile();
		assertThat(raffles.get(0).getBall1(), equalTo(1));
	}
	
	@Test
	public void testIsDate() throws Exception {
		assertThat(lotoImportService.isSomeDate("31231"), equalTo(false));
		assertThat(lotoImportService.isSomeDate("14/01/2014"), equalTo(true));
	}

}
