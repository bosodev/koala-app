package com.koala.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;

import com.koala.entity.Raffle;

public class RaffleDataAnalyticServiceTest {

	private RaffleDataAnalyticService raffleDataService = new RaffleDataAnalyticService();

	static Raffle getDefaultRaffle() {
		Raffle raffle = new Raffle();
		raffle.setBall1(1);
		raffle.setBall2(3);
		raffle.setBall3(4);
		raffle.setBall4(5);
		raffle.setBall5(6);
		raffle.setBall6(9);
		raffle.setBall7(10);
		raffle.setBall8(12);
		raffle.setBall9(15);
		raffle.setBall10(16);
		raffle.setBall11(20);
		raffle.setBall12(21);
		raffle.setBall13(22);
		raffle.setBall14(23);
		raffle.setBall15(25);
		return raffle;
	}

	static Raffle getDefaultRaffle2() {
		Raffle raffle = new Raffle();
		raffle.setBall1(1);
		raffle.setBall2(7);
		raffle.setBall3(8);
		raffle.setBall4(9);
		raffle.setBall5(10);
		raffle.setBall6(11);
		raffle.setBall7(12);
		raffle.setBall8(13);
		raffle.setBall9(14);
		raffle.setBall10(15);
		raffle.setBall11(20);
		raffle.setBall12(21);
		raffle.setBall13(22);
		raffle.setBall14(23);
		raffle.setBall15(25);
		return raffle;
	}

	@Test
	public void getSumNumbers() {
		assertThat(raffleDataService.sumNumbers(getDefaultRaffle()), equalTo(192));
	}

	@Test
	public void getAverage() {
		assertThat(raffleDataService.averageNumbers(getDefaultRaffle()), equalTo(12));
	}

	@Test
	public void getTotalPair() {
		Map<String, Integer> numbers = raffleDataService.getPairInteger(getDefaultRaffle());
		assertThat(numbers.get("pair"), equalTo(7));
		assertThat(numbers.get("unpaired"), equalTo(8));
	}

	@Test
	public void getGreaterSequence() {
		int bigSequence = raffleDataService.getGreaterSequence(getDefaultRaffle());
		int bigSequence2 = raffleDataService.getGreaterSequence(getDefaultRaffle2());
		assertThat(bigSequence, equalTo(4));
		assertThat(bigSequence2, equalTo(9));
	}

	@Test
	public void getSumRow() {
		int sumInterval1 = raffleDataService.rowSumTotal(1, 5, getDefaultRaffle(), true);
		assertThat(sumInterval1, equalTo(13));
		int sumInterval2 = raffleDataService.rowSumTotal(6, 10, getDefaultRaffle(), true);
		assertThat(sumInterval2, equalTo(25));
		int sumInterval3 = raffleDataService.rowSumTotal(11, 15, getDefaultRaffle(), true);
		assertThat(sumInterval3, equalTo(27));
		int sumInterval4 = raffleDataService.rowSumTotal(16, 20, getDefaultRaffle(), true);
		assertThat(sumInterval4, equalTo(36));
		int sumInterval5 = raffleDataService.rowSumTotal(21, 25, getDefaultRaffle(), true);
		assertThat(sumInterval5, equalTo(91));
	}

	@Test
	public void getTotalNumbersRow() {
		int sumInterval1 = raffleDataService.rowSumTotal(1, 5, getDefaultRaffle(), false);
		assertThat(sumInterval1, equalTo(4));
		int sumInterval2 = raffleDataService.rowSumTotal(6, 10, getDefaultRaffle(), false);
		assertThat(sumInterval2, equalTo(3));
		int sumInterval3 = raffleDataService.rowSumTotal(11, 15, getDefaultRaffle(), false);
		assertThat(sumInterval3, equalTo(2));
		int sumInterval4 = raffleDataService.rowSumTotal(16, 20, getDefaultRaffle(), false);
		assertThat(sumInterval4, equalTo(2));
		int sumInterval5 = raffleDataService.rowSumTotal(21, 25, getDefaultRaffle(), false);
		assertThat(sumInterval5, equalTo(4));
	}

}
