package com.koala.service;

import static com.koala.constants.ConstantsRaffle.ALL_NUMBERS;
import static com.koala.constants.ConstantsRaffle.FIRST_DOZEN_NUMBERS;
import static com.koala.constants.ConstantsRaffle.SECOND_DOZEN_NUMBERS;
import static com.koala.constants.ConstantsRaffle.THIRD_DOZEN_NUMBERS;
import static com.koala.entity.raffle.QRaffleDataAnalytic.raffleDataAnalytic;
import static com.koala.utils.KoalaUtils.asListRaffle;
import static com.koala.utils.KoalaUtils.populateRaffleNumbers;
import static com.koala.view.QViewLateByNumber.viewLateByNumber;
import static java.lang.Double.valueOf;
import static java.lang.Math.round;
import static java.util.Arrays.asList;
import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.koala.constants.ConstantsRaffle;
import com.koala.entity.raffle.QRaffleDataAnalytic;
import com.koala.entity.raffle.Raffle;
import com.koala.entity.raffle.RaffleDataAnalytic;
import com.koala.view.QViewLateByNumber;
import com.koala.view.ViewLateByNumber;
import com.koala.view.ViewNumberLessDrawn;
import com.koala.view.ViewNumberMoreDrawn;
import com.mysema.query.jpa.impl.JPAQuery;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class BuildGameService {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@EJB
	private HistoricService historicService;

	public Raffle randomRaffle() throws Exception {
		Raffle raffle = new Raffle();
		List<Integer> shuffle = shuffleAllNumbers();
		Collections.shuffle(shuffle);
		for (Integer i = 0; i < 15; i++) {
			Integer value = shuffle.get(i);
			populateRaffleNumbers(raffle, value, i);
		}
		return raffle;
	}

	public Raffle buildGameWithLateNumbers() throws Exception {
		List<ViewLateByNumber> lateNumbers = getNumbersLateMoreZero();
		if (lateNumbers.size() < 15)
			return mergeWithRandom(lateNumbers);
		return populateRaffleNumbers(asListView(lateNumbers));
	}

	public Raffle buildGameWithLessNumbers() throws Exception {
		Raffle raffle = new Raffle();
		List<ViewNumberLessDrawn> lessNumbers = historicService.listNumbersLessDrawn();
		int i = 0;
		for (ViewNumberLessDrawn number : lessNumbers)
			populateRaffleNumbers(raffle, number.getBall(), i++);
		return raffle;
	}

	public Raffle buildGameWithMoreNumbers() throws Exception {
		Raffle raffle = new Raffle();
		List<ViewNumberMoreDrawn> moreNumbers = historicService.listNumbersMoreDrawn();
		int i = 0;
		for (ViewNumberMoreDrawn number : moreNumbers)
			populateRaffleNumbers(raffle, number.getBall(), i++);
		return raffle;
	}

	public Raffle buildRandomWithOutNumber(Integer withOutNumber) throws Exception {
		Integer withOutNumbers[] = new Integer[] { withOutNumber, withOutNumber + 10, withOutNumber + 20 };
		List<Integer> listRaffle = asListRaffle(randomRaffle());
		listRaffle.removeAll(asList(withOutNumbers));
		if (listRaffle.size() < 15)
			addOtherNumbers(listRaffle, asList(withOutNumbers));
		return populateRaffleNumbers(listRaffle);
	}

	public Raffle buildWithPairUnpaired(Integer maxUnpair) throws Exception {
		List<Integer> values = new ArrayList<Integer>();
		randomWithPairUnpaired(ConstantsRaffle.PAIR_NUMBERS, 15 - maxUnpair, values);
		randomWithPairUnpaired(ConstantsRaffle.UNPAIRED_NUMBERS, maxUnpair, values);
		return populateRaffleNumbers(values);
	}

	public Raffle buildGameByDozens(int firstDozen, int thirdDozen) throws Exception {
		List<Integer> shuffleFirstDozen = shuffleFirstDozen();
		List<Integer> shuffleSecondDozen = shuffleSecondDozen();
		List<Integer> shuffleThirdDozen = shuffleThirdDozen();
		List<Integer> values = new ArrayList<Integer>();
		populateFirstDozen(firstDozen, shuffleFirstDozen, values);
		populateFirstDozen(thirdDozen, shuffleThirdDozen, values);
		fillNumbers(shuffleSecondDozen, values);
		return populateRaffleNumbers(values);
	}

	public Raffle getAVGPairsByConcurses(Integer concurses) throws Exception {
		QRaffleDataAnalytic dataAnalytic = raffleDataAnalytic;
		JPAQuery subQuery = new JPAQuery(entityManager);
		JPAQuery query = new JPAQuery(entityManager);
		RaffleDataAnalytic raffleAnalytic = getAvgTypeNumber(concurses, dataAnalytic, subQuery, query);
		return buildWithPairUnpaired(valueOf(round(raffleAnalytic.getAvgUnPaired())).intValue());
	}

	public Raffle buildGameBasedLastRaffle() throws Exception {
		Raffle raffle = historicService.getLastRaffle();
		List<Integer> pairs = new ArrayList<Integer>();
		List<Integer> unPairs = new ArrayList<Integer>();
		List<Integer> values = asListRaffle(raffle);
		sepairNumbers(values, pairs, unPairs);
		values = chooseNumbersLastRaffle(pairs, unPairs);
		loadNumbers(values);
		return populateRaffleNumbers(values);
	}

	protected List<ViewLateByNumber> getNumbersLateMoreZero() {
		QViewLateByNumber lateNumbers = viewLateByNumber;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(lateNumbers).where(lateNumbers.total.ne(0)).list(lateNumbers);
	}

	private Raffle mergeWithRandom(List<ViewLateByNumber> lateNumbers) throws Exception {
		List<Integer> values = asListView(lateNumbers);
		if (values.size() < 15) {
			List<Integer> shuffle = shuffleAllNumbers();
			shuffle(shuffle);
			for (Integer number : shuffle) {
				values.add(number);
				if (values.size() >= 15)
					break;
			}
		}
		return populateRaffleNumbers(values);
	}

	private List<Integer> asListView(List<ViewLateByNumber> lateNumbers) {
		List<Integer> values = new ArrayList<Integer>();
		for (ViewLateByNumber late : lateNumbers)
			values.add(late.getBall());
		return values;
	}

	private List<Integer> addOtherNumbers(List<Integer> values, List<Integer> randomWithOutNumbers) {
		for (Integer num : shuffleAllNumbers()) {
			if (!randomWithOutNumbers.contains(num) && values.size() < 15)
				values.add(num);
		}
		return values;
	}

	private void randomWithPairUnpaired(Integer pairUnpaired[], Integer maxPairs, List<Integer> values) {
		List<Integer> shuffle = asList(pairUnpaired);
		Collections.shuffle(shuffle);
		for (Integer i = 0; i < maxPairs; i++) {
			Integer v = shuffle.get(i);
			values.add(v);
		}
	}

	private List<Integer> chooseNumbersLastRaffle(List<Integer> pairs, List<Integer> unPairs) {
		List<Integer> lastRaffles = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			lastRaffles.add(pairs.get(i));
			lastRaffles.add(unPairs.get(i));
		}
		return lastRaffles;
	}

	private void loadNumbers(List<Integer> values) {
		List<Integer> shuffleValues = shuffleAllNumbers();
		for (Integer num : shuffleValues) {
			if (!values.contains(num))
				values.add(num);
			if (values.size() == 15)
				break;
		}
	}

	private List<Integer> shuffleAllNumbers() {
		List<Integer> shuffleValues = Arrays.asList(ALL_NUMBERS);
		Collections.shuffle(shuffleValues);
		return shuffleValues;
	}

	private List<Integer> shuffleFirstDozen() {
		List<Integer> shuffleValues = Arrays.asList(FIRST_DOZEN_NUMBERS);
		Collections.shuffle(shuffleValues);
		return shuffleValues;
	}

	private List<Integer> shuffleSecondDozen() {
		List<Integer> shuffleValues = Arrays.asList(SECOND_DOZEN_NUMBERS);
		Collections.shuffle(shuffleValues);
		return shuffleValues;
	}

	private List<Integer> shuffleThirdDozen() {
		List<Integer> shuffleValues = Arrays.asList(THIRD_DOZEN_NUMBERS);
		Collections.shuffle(shuffleValues);
		return shuffleValues;
	}

	private void sepairNumbers(List<Integer> values, List<Integer> pairs, List<Integer> unPairs) {
		for (Integer num : values) {
			if (num % 2 == 0)
				pairs.add(num);
			else
				unPairs.add(num);

		}
		Collections.shuffle(pairs);
		Collections.shuffle(unPairs);
	}

	private void fillNumbers(List<Integer> shuffleSecondDozen, List<Integer> values) {
		for (int i = 0; i <= 10; i++)
			if (values.size() < 15)
				values.add(shuffleSecondDozen.get(i));
	}

	private void populateFirstDozen(int firstDozen, List<Integer> dozens, List<Integer> values) {
		for (int i = 0; i <= firstDozen; i++)
			values.add(dozens.get(i));
	}

	private RaffleDataAnalytic getAvgTypeNumber(Integer concurses, QRaffleDataAnalytic dataAnalytic, JPAQuery subQuery, JPAQuery query) {
		RaffleDataAnalytic raffleAnalytic = query.from(dataAnalytic).where(dataAnalytic.concurse.gt(subQuery.from(dataAnalytic).singleResult(dataAnalytic.concurse.max()) - concurses)).singleResult(QRaffleDataAnalytic.create(dataAnalytic.pair.avg(), dataAnalytic.unpaired.avg()));
		return raffleAnalytic;
	}

}
