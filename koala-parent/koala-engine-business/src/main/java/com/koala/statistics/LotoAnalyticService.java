package com.koala.statistics;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.koala.data.DataObject;

public class LotoAnalyticService {

	public Integer getTypesNumbersRaffle(Collection<Integer> numbers, Boolean typePar) {
		Integer typeNumber = 0;
		for (Integer num : numbers) {
			if (typePar && num % 2 == 0) {
				typeNumber++;
			} else if (!typePar && num % 2 != 0) {
				typeNumber++;
			}
		}
		return typeNumber;
	}

	public Map<Integer, Integer> getRafflesAllNumber(List<DataObject> raffle) {
		Map<Integer, Integer> qtdNumbers = new HashMap<Integer, Integer>();
		for (int i = 1; i <= 25; i++) {
			Integer count = findByRaffle(raffle, i);
			qtdNumbers.put(i, count);
		}
		return qtdNumbers;
	}

	private Integer findByRaffle(List<DataObject> raffle, Integer i) {
		Integer count = 0;
		for (DataObject data : raffle) {
			List<Integer> numbers = getNumbersRaffle(data);
			if (numbers.contains(i))
				count++;
		}
		return count;
	}

	public List<Integer> getRafflesByNumber(Integer raffleNumber, List<DataObject> raffle) {
		List<Integer> rafflesWithNumber = new ArrayList<Integer>();
		for (DataObject data : raffle) {
			List<Integer> numbers = getNumbersRaffle(data);
			if (numbers.contains(raffleNumber))
				rafflesWithNumber.add(raffleNumber);
		}
		return rafflesWithNumber;
	}

	public List<Integer> getNumbersRaffle(DataObject data) {
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(data.getBall1());
		numbers.add(data.getBall2());
		numbers.add(data.getBall3());
		numbers.add(data.getBall4());
		numbers.add(data.getBall5());
		numbers.add(data.getBall6());
		numbers.add(data.getBall7());
		numbers.add(data.getBall8());
		numbers.add(data.getBall9());
		numbers.add(data.getBall10());
		numbers.add(data.getBall11());
		numbers.add(data.getBall12());
		numbers.add(data.getBall13());
		numbers.add(data.getBall14());
		numbers.add(data.getBall15());
		return numbers;
	}

	protected double fatorial(double numberFatorial) {
		if (numberFatorial < 0)
			throw new InvalidParameterException("Numero menor que 0, impossivel fatorial");
		double f = numberFatorial;
		while (numberFatorial > 1) {
			f = f * (numberFatorial - 1);
			numberFatorial--;
		}
		return f;
	}

	public double getPossibleCombinations(long amostralSpace, long qtdNumbers) {
		double numerator = fatorial(amostralSpace);
		double denominator = fatorial(qtdNumbers) * fatorial((amostralSpace - qtdNumbers));
		double possibilities = numerator / denominator;
		return Math.round(possibilities);
	}

	public Integer getQtdRaffleNumberNotExist(List<DataObject> objs, Integer number) {
		Integer raffleNumber = 0;
		Integer lastRaflle = 0;
		for (DataObject obj : objs) {
			if (getNumbersRaffle(obj).contains(number))
				raffleNumber = obj.getConcurse();
			lastRaflle = obj.getConcurse();
		}
		if (raffleNumber == 0)
			return objs.size();
		return lastRaflle - raffleNumber;
	}
}
