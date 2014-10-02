package com.koala.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.koala.entity.raffle.Raffle;
import com.koala.view.ViewLateByNumber;
import com.koala.view.ViewNumberLessDrawn;
import com.koala.view.ViewNumberMoreDrawn;

public class BuildGameServiceTest {

	private BuildGameService buildGameService;

	@Mock
	private HistoricService historicService;

	@Mock
	private EntityManager em;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		buildGameService = spy(new BuildGameService(em, historicService));
	}

	@Test
	public void random() throws Exception {
		Raffle raffle = buildGameService.randomRaffle();
		tests(raffle);
	}

	private void tests(Raffle raffle) {
		assertThat(raffle.getBall1(), notNullValue());
		assertThat(raffle.getBall2(), notNullValue());
		assertThat(raffle.getBall3(), notNullValue());
		assertThat(raffle.getBall4(), notNullValue());
		assertThat(raffle.getBall5(), notNullValue());
		assertThat(raffle.getBall6(), notNullValue());
		assertThat(raffle.getBall7(), notNullValue());
		assertThat(raffle.getBall8(), notNullValue());
		assertThat(raffle.getBall9(), notNullValue());
		assertThat(raffle.getBall10(), notNullValue());
		assertThat(raffle.getBall11(), notNullValue());
		assertThat(raffle.getBall12(), notNullValue());
		assertThat(raffle.getBall13(), notNullValue());
		assertThat(raffle.getBall14(), notNullValue());
		assertThat(raffle.getBall15(), notNullValue());
	}

	@Test
	public void testBuildByLastRaffle() throws Exception {
		when(historicService.getLastRaffle()).thenReturn(getRaffle());
		tests(buildGameService.buildGameBasedLastRaffle());
	}

	@Test
	public void testBuildByDozens() throws Exception {
		tests(buildGameService.buildGameByDozens(3, 4));
	}

	@Test
	public void testBuildByLateNumbers() throws Exception {
		doReturn(getListLateNumbers()).when(buildGameService).getNumbersLateMoreZero();
		tests(buildGameService.buildGameWithLateNumbers());
	}

	@Test
	public void testBuildByLessNumbersRaffle() throws Exception {
		when(historicService.listNumbersLessDrawn()).thenReturn(getNumbersLessDrawn());
		tests(buildGameService.buildGameWithLessNumbers());
	}

	@Test
	public void testBuildByMoreNumbersRaffle() throws Exception {
		when(historicService.listNumbersMoreDrawn()).thenReturn(getNumbersMoreDrawn());
		tests(buildGameService.buildGameWithMoreNumbers());
	}
	
	@Test
	public void testBuildByPairUnpaired() throws Exception {
		tests(buildGameService.buildWithPairUnpaired(7));
	}
	
	@Test
	public void testBuildWithoutNumber() throws Exception {
		tests(buildGameService.buildRandomWithOutNumber(5));
	}


	private Raffle getRaffle() {
		return Raffle.builder().ball1(1).ball2(2).ball3(4).ball4(5).ball5(6).ball6(8).ball7(10).ball8(11).ball9(12).ball10(13).ball11(15).ball12(17).ball13(19).ball14(20).ball15(24).build();
	}

	private List<ViewLateByNumber> getListLateNumbers() {
		List<ViewLateByNumber> numbers = new ArrayList<ViewLateByNumber>();
		ViewLateByNumber number1 = ViewLateByNumber.builder().ball(3).total(3).build();
		ViewLateByNumber number2 = ViewLateByNumber.builder().ball(5).total(4).build();
		ViewLateByNumber number3 = ViewLateByNumber.builder().ball(17).total(2).build();
		ViewLateByNumber number4 = ViewLateByNumber.builder().ball(10).total(5).build();
		numbers.add(number1);
		numbers.add(number2);
		numbers.add(number3);
		numbers.add(number4);
		return numbers;
	}

	private List<ViewNumberLessDrawn> getNumbersLessDrawn() {
		List<ViewNumberLessDrawn> numbers = new ArrayList<ViewNumberLessDrawn>();
		numbers.add(ViewNumberLessDrawn.builder().ball(2).total(856).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(3).total(857).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(4).total(858).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(5).total(858).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(6).total(860).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(8).total(862).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(10).total(874).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(11).total(878).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(12).total(877).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(13).total(877).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(15).total(877).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(16).total(877).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(17).total(877).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(20).total(877).build());
		numbers.add(ViewNumberLessDrawn.builder().ball(21).total(877).build());
		return numbers;
	}

	private List<ViewNumberMoreDrawn> getNumbersMoreDrawn() {
		List<ViewNumberMoreDrawn> numbers = new ArrayList<ViewNumberMoreDrawn>();
		numbers.add(ViewNumberMoreDrawn.builder().ball(1).total(656).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(2).total(658).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(5).total(678).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(6).total(679).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(8).total(680).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(10).total(681).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(11).total(682).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(12).total(685).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(16).total(687).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(20).total(690).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(21).total(691).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(22).total(692).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(23).total(694).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(24).total(696).build());
		numbers.add(ViewNumberMoreDrawn.builder().ball(25).total(698).build());
		return numbers;
	}

}
