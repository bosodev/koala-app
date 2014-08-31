package com.koala.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.koala.constants.ConstantsRaffle;
import com.koala.data.DataBuildGame;
import com.koala.data.DataGameFactory;
import com.koala.entity.Raffle;
import com.koala.views.QViewLateByNumber;
import com.koala.views.ViewLateByNumber;
import com.koala.views.ViewNumberLessDrawn;
import com.koala.views.ViewNumberMoreDrawn;
import com.mysema.query.jpa.impl.JPAQuery;

@Stateless
public class LotoBuildGameService {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@EJB
	private LotoImportService lotoImportService;

	@EJB
	private HistoricService historicService;

	public DataBuildGame randomRaffle() {
		DataBuildGame dataGame = DataGameFactory.buildGameData();
		List<Integer> shuffle = shuffleAllNumbers();
		Collections.shuffle(shuffle);
		for (Integer i = 0; i < 15; i++) {
			Integer v = shuffle.get(i);
			dataGame.getNumbers().add(v);
		}
		return dataGame;
	}

	private List<ViewLateByNumber> getNumbersLateMoreZero() {
		QViewLateByNumber lateNumbers = QViewLateByNumber.viewLateByNumber;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(lateNumbers).where(lateNumbers.total.ne(0)).list(lateNumbers);
	}

	private DataBuildGame mergeWithRandom(List<ViewLateByNumber> lateNumbers) {
		DataBuildGame dataGame = parseToData(lateNumbers);
		if (dataGame.getNumbers().size() < 15) {
			List<Integer> shuffle = shuffleAllNumbers();
			Collections.shuffle(shuffle);
			for (Integer number : shuffle) {
				dataGame.getNumbers().add(number);
				if (dataGame.getNumbers().size() >= 15)
					break;
			}
		}
		return dataGame;
	}

	private DataBuildGame parseToData(List<ViewLateByNumber> lateNumbers) {
		DataBuildGame dataGame = DataGameFactory.buildGameData();
		for (ViewLateByNumber late : lateNumbers)
			dataGame.getNumbers().add(late.getBall());
		return dataGame;
	}

	public DataBuildGame buildGameWithLateNumbers() {
		List<ViewLateByNumber> lateNumbers = getNumbersLateMoreZero();
		if (lateNumbers.size() < 15)
			return mergeWithRandom(lateNumbers);
		return parseToData(lateNumbers);
	}

	public DataBuildGame buildGameWithLessNumbers() {
		DataBuildGame dataGame = DataGameFactory.buildGameData();
		List<ViewNumberLessDrawn> lessNumbers = historicService.listNumbersLessDrawn();
		for (ViewNumberLessDrawn number : lessNumbers)
			dataGame.getNumbers().add(number.getBall());
		return dataGame;
	}

	public DataBuildGame buildGameWithMoreNumbers() {
		DataBuildGame dataGame = DataGameFactory.buildGameData();
		List<ViewNumberMoreDrawn> moreNumbers = historicService.listNumbersMoreDrawn();
		for (ViewNumberMoreDrawn number : moreNumbers)
			dataGame.getNumbers().add(number.getBall());
		return dataGame;
	}

	public DataBuildGame buildRandomWithOutNumber(Integer withOutNumber) {
		Integer withOutNumbers[] = new Integer[] { withOutNumber, withOutNumber + 10, withOutNumber + 20 };
		DataBuildGame dataBuildGame = randomRaffle();
		dataBuildGame.getNumbers().removeAll(Arrays.asList(withOutNumbers));
		if (dataBuildGame.getNumbers().size() < 15)
			addOtherNumbers(dataBuildGame, Arrays.asList(withOutNumbers));
		return dataBuildGame;
	}

	private DataBuildGame addOtherNumbers(DataBuildGame dataBuildGame, List<Integer> randomWithOutNumbers) {
		for (Integer num : randomRaffle().getNumbers()) {
			if (!randomWithOutNumbers.contains(num) && dataBuildGame.getNumbers().size() < 15)
				dataBuildGame.getNumbers().add(num);
		}
		return dataBuildGame;
	}

	public DataBuildGame buildWithPairUnpaired(Integer maxUnpair) {
		DataBuildGame dataGame = DataGameFactory.buildGameData();
		DataBuildGame dataGamePair = randomWithPairUnpaired(ConstantsRaffle.PAIR_NUMBERS, 15 - maxUnpair, dataGame);
		DataBuildGame dataGameUnPaired = randomWithPairUnpaired(ConstantsRaffle.UNPAIRED_NUMBERS, maxUnpair, dataGame);
		dataGamePair.getNumbers().addAll(dataGameUnPaired.getNumbers());
		return dataGamePair;
	}

	private DataBuildGame randomWithPairUnpaired(Integer pairUnpaired[], Integer maxPairs, DataBuildGame dataGame) {
		List<Integer> shuffle = Arrays.asList(pairUnpaired);
		Collections.shuffle(shuffle);
		for (Integer i = 0; i < maxPairs; i++) {
			Integer v = shuffle.get(i);
			dataGame.getNumbers().add(v);
		}
		return dataGame;
	}

	private DataBuildGame buildRaffleToData(Raffle raffle) {
		DataBuildGame data = new DataBuildGame();
		data.getNumbers().add(raffle.getBall1());
		data.getNumbers().add(raffle.getBall2());
		data.getNumbers().add(raffle.getBall3());
		data.getNumbers().add(raffle.getBall4());
		data.getNumbers().add(raffle.getBall5());
		data.getNumbers().add(raffle.getBall6());
		data.getNumbers().add(raffle.getBall7());
		data.getNumbers().add(raffle.getBall8());
		data.getNumbers().add(raffle.getBall9());
		data.getNumbers().add(raffle.getBall10());
		data.getNumbers().add(raffle.getBall11());
		data.getNumbers().add(raffle.getBall12());
		data.getNumbers().add(raffle.getBall13());
		data.getNumbers().add(raffle.getBall14());
		data.getNumbers().add(raffle.getBall15());
		return data;
	}

	public DataBuildGame buildGameBasedLastRaffle() {
		Raffle raffle = historicService.getLastRaffle();
		DataBuildGame buildDatagame = buildRaffleToData(raffle);
		List<Integer> pairs = new ArrayList<Integer>();
		List<Integer> unPairs = new ArrayList<Integer>();
		sepairNumbers(buildDatagame, pairs, unPairs);
		buildDatagame = chooseNumbersLastRaffle(pairs, unPairs);
		loadNumbers(buildDatagame);
		return buildDatagame;
	}

	private DataBuildGame chooseNumbersLastRaffle(List<Integer> pairs, List<Integer> unPairs) {
		DataBuildGame buildgame = DataGameFactory.buildGameData();
		for (int i = 0; i < 3; i++) {
			buildgame.getNumbers().add(pairs.get(i));
			buildgame.getNumbers().add(unPairs.get(i));
		}
		return buildgame;
	}

	private void loadNumbers(DataBuildGame buildDatagame) {
		List<Integer> shuffleValues = shuffleAllNumbers();
		for (Integer num : shuffleValues) {
			if (!buildDatagame.getNumbers().contains(num))
				buildDatagame.getNumbers().add(num);
			if (buildDatagame.getNumbers().size() == 15)
				break;
		}
	}

	private List<Integer> shuffleAllNumbers() {
		List<Integer> shuffleValues = Arrays.asList(ConstantsRaffle.ALL_NUMBERS);
		Collections.shuffle(shuffleValues);
		return shuffleValues;
	}

	private List<Integer> shuffleFirstDozen() {
		List<Integer> shuffleValues = Arrays.asList(ConstantsRaffle.FIRST_DOZEN_NUMBERS);
		Collections.shuffle(shuffleValues);
		return shuffleValues;
	}

	private List<Integer> shuffleSecondDozen() {
		List<Integer> shuffleValues = Arrays.asList(ConstantsRaffle.SECOND_DOZEN_NUMBERS);
		Collections.shuffle(shuffleValues);
		return shuffleValues;
	}

	private List<Integer> shuffleThirdDozen() {
		List<Integer> shuffleValues = Arrays.asList(ConstantsRaffle.THIRD_DOZEN_NUMBERS);
		Collections.shuffle(shuffleValues);
		return shuffleValues;
	}

	private void sepairNumbers(DataBuildGame buildDatagame, List<Integer> pairs, List<Integer> unPairs) {
		for (Integer num : buildDatagame.getNumbers()) {
			if (num % 2 == 0)
				pairs.add(num);
			else
				unPairs.add(num);

		}
		Collections.shuffle(pairs);
		Collections.shuffle(unPairs);
	}

	public DataBuildGame buildGameByDozens(int firstDozen, int thirdDozen) {
		List<Integer> shuffleFirstDozen = shuffleFirstDozen();
		List<Integer> shuffleSecondDozen = shuffleSecondDozen();
		List<Integer> shuffleThirdDozen = shuffleThirdDozen();
		DataBuildGame dataBuildGame = DataGameFactory.buildGameData();
		for (int i = 0; i <= firstDozen; i++)
			dataBuildGame.getNumbers().add(shuffleFirstDozen.get(i));
		for (int i = 0; i <= thirdDozen; i++)
			dataBuildGame.getNumbers().add(shuffleThirdDozen.get(i));
		for (int i = 0; i <= 10; i++)
			if (dataBuildGame.getNumbers().size() < 15)
				dataBuildGame.getNumbers().add(shuffleSecondDozen.get(i));
		return dataBuildGame;
	}

}
