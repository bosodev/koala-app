package com.koala.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;

import com.koala.constants.ConstantsRaffle;
import com.koala.entity.Raffle;

@Stateless
public class RaffleDataAnalyticService {

	protected Set<Integer> getDataSet(Raffle raffle) {
		Set<Integer> numbers = new TreeSet<Integer>();
		numbers.add(raffle.getBall1());
		numbers.add(raffle.getBall2());
		numbers.add(raffle.getBall3());
		numbers.add(raffle.getBall4());
		numbers.add(raffle.getBall5());
		numbers.add(raffle.getBall6());
		numbers.add(raffle.getBall7());
		numbers.add(raffle.getBall8());
		numbers.add(raffle.getBall9());
		numbers.add(raffle.getBall10());
		numbers.add(raffle.getBall11());
		numbers.add(raffle.getBall12());
		numbers.add(raffle.getBall13());
		numbers.add(raffle.getBall14());
		numbers.add(raffle.getBall15());
		return numbers;
	}

	protected Integer sumSecondColumn(Raffle raffle) {
		return raffle.getBall6() + raffle.getBall7() + raffle.getBall8() + raffle.getBall9() + raffle.getBall10();
	}

	protected Integer sumThirdColumn(Raffle raffle) {
		return raffle.getBall11() + raffle.getBall12() + raffle.getBall13() + raffle.getBall14() + raffle.getBall15();
	}

	public Integer sumNumbers(Raffle raffle) {
		Integer sum = 0;
		for (Integer num : getDataSet(raffle))
			sum += num;
		return sum;
	}

	public Integer averageNumbers(Raffle raffle) {
		return sumNumbers(raffle) / ConstantsRaffle.TOTAL_NUMBERS_LOTO_MIN_PLAY;
	}

	public Map<String, Integer> getPairInteger(Raffle raffle) {
		Map<String, Integer> pairUnpaired = new HashMap<String, Integer>();
		Integer pair = 0;
		pair = getPairs(raffle, pair);
		pairUnpaired.put("pair", pair);
		pairUnpaired.put("unpaired", 15 - pair);
		return pairUnpaired;
	}

	private Integer getPairs(Raffle raffle, Integer pair) {
		for (Integer num : getDataSet(raffle))
			if (num % 2 == 0)
				pair++;
		return pair;
	}

	public Integer getGreaterSequence(Raffle raffle) {
		List<Integer> sequences = new ArrayList<Integer>();
		SortedSet<Integer> numbers = (SortedSet<Integer>) getDataSet(raffle);
		List<Integer> nums = new ArrayList<Integer>();
		nums.addAll(numbers);
		for (int i = 0; i < nums.size(); i++) {
			if (i + 1 != nums.size()) {
				Integer diff = nums.get(i + 1) - nums.get(i);
				sequences.add(diff);
			}
		}
		return countBigSequence(sequences);
	}

	private Integer countBigSequence(List<Integer> sequences) {
		SortedSet<Integer> sequ = new TreeSet<Integer>();
		Integer count = 0;
		for (Integer seq : sequences) {
			if (seq == 1)
				count++;
			else {
				sequ.add(count);
				count = 0;
				continue;
			}
		}
		return sequ.last() + 1;
	}

	public Integer rowSumTotal(Integer firstNumberRow, Integer lastNumberRow, Raffle raffle, Boolean isSum) {
		SortedSet<Integer> numbers = (SortedSet<Integer>) getDataSet(raffle);
		Integer sumInterval = 0;
		for (Integer num : numbers) {
			if (num >= firstNumberRow && num <= lastNumberRow && isSum)
				sumInterval += num;
			else if (num >= firstNumberRow && num <= lastNumberRow && !isSum)
				sumInterval += 1;
		}
		return sumInterval;
	}

}