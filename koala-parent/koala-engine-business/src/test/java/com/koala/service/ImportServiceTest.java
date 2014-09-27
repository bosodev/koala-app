package com.koala.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.koala.entity.Raffle;
import com.koala.utils.DateUtils;

public class ImportServiceTest {

	@Test
	public void getRaffleData() throws Exception {
		ImportService lotoImportService = new ImportService();
		Raffle raffle = lotoImportService.getData("xml", 1);
		assertThat(raffle.getBall1(), equalTo(2));
		assertThat(raffle.getBall2(), equalTo(3));
		assertThat(raffle.getBall3(), equalTo(5));
		assertThat(raffle.getBall4(), equalTo(6));
		assertThat(raffle.getBall5(), equalTo(9));
		assertThat(raffle.getBall6(), equalTo(10));
		assertThat(raffle.getBall7(), equalTo(11));
		assertThat(raffle.getBall8(), equalTo(13));
		assertThat(raffle.getBall9(), equalTo(14));
		assertThat(raffle.getBall10(), equalTo(16));
		assertThat(raffle.getBall11(), equalTo(18));
		assertThat(raffle.getBall12(), equalTo(20));
		assertThat(raffle.getBall13(), equalTo(23));
		assertThat(raffle.getBall14(), equalTo(24));
		assertThat(raffle.getBall15(), equalTo(25));
		
		assertThat(raffle.getConcurse(), equalTo(1));
		assertThat(raffle.getDate(), equalTo(DateUtils.formatRaffleDate("29/09/2003")));

	}

}
