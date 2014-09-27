package com.koala.statistics;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.koala.data.DataObject;

public class AnalyticTest {

	private LotoAnalyticService lotoAnalyticService = new LotoAnalyticService();

	private DataObject raffleNumbers() {
		DataObject dataObject = new DataObject();
		dataObject.setBall1(1);
		dataObject.setBall2(2);
		dataObject.setBall3(3);
		dataObject.setBall4(5);
		dataObject.setBall5(6);
		dataObject.setBall6(7);
		dataObject.setBall7(8);
		dataObject.setBall8(13);
		dataObject.setBall9(14);
		dataObject.setBall10(17);
		dataObject.setBall11(19);
		dataObject.setBall12(20);
		dataObject.setBall13(21);
		dataObject.setBall14(22);
		dataObject.setBall15(23);

		dataObject.setConcurse(998);
		dataObject.setDate(new Date());

		return dataObject;
	}

	private DataObject raffle2Numbers() {
		DataObject dataObject = new DataObject();
		dataObject.setBall1(3);
		dataObject.setBall2(4);
		dataObject.setBall3(5);
		dataObject.setBall4(6);
		dataObject.setBall5(7);
		dataObject.setBall6(8);
		dataObject.setBall7(10);
		dataObject.setBall8(12);
		dataObject.setBall9(13);
		dataObject.setBall10(17);
		dataObject.setBall11(19);
		dataObject.setBall12(20);
		dataObject.setBall13(21);
		dataObject.setBall14(22);
		dataObject.setBall15(23);

		dataObject.setConcurse(999);
		dataObject.setDate(new Date());

		return dataObject;
	}

	private DataObject raffle3Numbers() {
		DataObject dataObject = new DataObject();
		dataObject.setBall1(1);
		dataObject.setBall2(2);
		dataObject.setBall3(3);
		dataObject.setBall4(5);
		dataObject.setBall5(6);
		dataObject.setBall6(7);
		dataObject.setBall7(8);
		dataObject.setBall8(10);
		dataObject.setBall9(14);
		dataObject.setBall10(17);
		dataObject.setBall11(19);
		dataObject.setBall12(21);
		dataObject.setBall13(22);
		dataObject.setBall14(24);
		dataObject.setBall15(25);

		dataObject.setConcurse(1000);
		dataObject.setDate(new Date());

		return dataObject;
	}

	private DataObject raffle4Numbers() {
		DataObject dataObject = new DataObject();
		dataObject.setBall1(1);
		dataObject.setBall2(2);
		dataObject.setBall3(3);
		dataObject.setBall14(4);
		dataObject.setBall5(6);
		dataObject.setBall6(7);
		dataObject.setBall7(8);
		dataObject.setBall8(10);
		dataObject.setBall9(11);
		dataObject.setBall10(17);
		dataObject.setBall11(19);
		dataObject.setBall12(20);
		dataObject.setBall13(21);
		dataObject.setBall14(22);
		dataObject.setBall15(23);

		dataObject.setConcurse(1001);
		dataObject.setDate(new Date());

		return dataObject;
	}

	private DataObject raffle5Numbers() {
		DataObject dataObject = new DataObject();
		dataObject.setBall1(2);
		dataObject.setBall2(4);
		dataObject.setBall3(5);
		dataObject.setBall4(8);
		dataObject.setBall5(10);
		dataObject.setBall6(11);
		dataObject.setBall7(13);
		dataObject.setBall8(15);
		dataObject.setBall9(17);
		dataObject.setBall10(18);
		dataObject.setBall10(19);
		dataObject.setBall11(21);
		dataObject.setBall12(22);
		dataObject.setBall13(23);
		dataObject.setBall14(24);
		dataObject.setBall15(25);
		dataObject.setConcurse(1002);
		dataObject.setDate(new Date());
		return dataObject;
	}

	@Test
	public void statitics() {
		assertThat(lotoAnalyticService.getTypesNumbersRaffle(lotoAnalyticService.getNumbersRaffle(raffle2Numbers()), true), equalTo(7));
		assertThat(lotoAnalyticService.getTypesNumbersRaffle(lotoAnalyticService.getNumbersRaffle(raffle2Numbers()), false), equalTo(8));
	}

	@Test
	public void countNumberRafflesByNumber() {
		List<DataObject> raffles = realRaffles();

		List<Integer> rafflesByNumbers = lotoAnalyticService.getRafflesByNumber(4, raffles);

		List<Integer> raffles2ByNumbers = lotoAnalyticService.getRafflesByNumber(3, raffles);

		List<Integer> raffles3ByNumbers = lotoAnalyticService.getRafflesByNumber(25, raffles);

		assertThat(rafflesByNumbers.size(), equalTo(2));
		assertThat(raffles2ByNumbers.size(), equalTo(4));
		assertThat(raffles3ByNumbers.size(), equalTo(2));
	}

	private List<DataObject> realRaffles() {
		List<DataObject> raffles = new ArrayList<DataObject>();
		raffles.add(raffleNumbers());
		raffles.add(raffle2Numbers());
		raffles.add(raffle3Numbers());
		raffles.add(raffle4Numbers());
		raffles.add(raffle5Numbers());
		return raffles;
	}

	@Test
	public void countNumbersByRaffle() {
		Map<Integer, Integer> raffles3ByNumbers = lotoAnalyticService.getRafflesAllNumber(realRaffles());
		assertThat(raffles3ByNumbers.get(1), equalTo(3));
		assertThat(raffles3ByNumbers.get(2), equalTo(4));
		assertThat(raffles3ByNumbers.get(23), equalTo(4));
		assertThat(raffles3ByNumbers.get(25), equalTo(2));
	}

	@Test
	public void fatorial() {
		assertThat(lotoAnalyticService.fatorial(3), equalTo(6d));
		assertThat(lotoAnalyticService.fatorial(5), equalTo(120d));
	}

	@Test(expected = InvalidParameterException.class)
	public void fatorialError() {
		assertThat(lotoAnalyticService.fatorial(-12), equalTo(120d));
	}

	@Test
	public void combinations() {
		assertThat(lotoAnalyticService.getPossibleCombinations(25, 15), equalTo(3268760d));
		assertThat(lotoAnalyticService.getPossibleCombinations(25, 18), equalTo(480700d));
	}

	@Test
	public void getQtdRaffleNumberNotExist() {
		assertThat(lotoAnalyticService.getQtdRaffleNumberNotExist(realRaffles(), 2), equalTo(0));
		assertThat(lotoAnalyticService.getQtdRaffleNumberNotExist(realRaffles(), 1), equalTo(1));
		assertThat(lotoAnalyticService.getQtdRaffleNumberNotExist(realRaffles(), 1), equalTo(1));
		assertThat(lotoAnalyticService.getQtdRaffleNumberNotExist(realRaffles(), 9), equalTo(5));
	}

}
