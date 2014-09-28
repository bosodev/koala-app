package com.koala.service;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.koala.entity.QRaffleDataAnalytic;
import com.koala.entity.Raffle;
import com.koala.entity.RaffleDataAnalytic;
import com.koala.exceptions.RuleException;
import com.mysema.query.jpa.impl.JPAQuery;

public class RulesService {

	@EJB
	private AnalyticService analyticService;

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	public Raffle validator(Raffle raffle, int range) throws RuleException {
		RaffleDataAnalytic dataAnalitic = analyticService.getRaffleDataAnality(raffle);
		getAnalysis(range).stream().forEach(analitic -> {
			try {
				filterRule(analitic, dataAnalitic);
			} catch (RuleException e) {
				throw new RuntimeException("Rule Exception : " + e);
			}
		});
		return raffle;
	}

	protected void filterRule(RaffleDataAnalytic analitic, RaffleDataAnalytic dataAnalitic) throws RuleException {
		if (analitic.getAverage().equals(dataAnalitic.getAverage()))
			throw new RuleException("Media de numeros iguais, sorteio: " + analitic.getConcurse() +" Media :"+ analitic.getAverage());
		if (analitic.getSum().equals(dataAnalitic.getSum()))
			throw new RuleException("Somatoria dos numeros iguais, sorteio: " + analitic.getConcurse()+" Somatoria :"+analitic.getSum());
		if (analitic.getGreaterSequence().equals(dataAnalitic.getGreaterSequence()))
			throw new RuleException("Maior sequencia iguais, sorteio: " + analitic.getConcurse()+ " Quantidade da sequencia :" + analitic.getGreaterSequence());
		if (isPairUnpairedEquals(analitic, dataAnalitic))
			throw new RuleException("Pares e impares iguais, sorteio: " + analitic.getConcurse() +" Pares :"+ analitic.getPair() +"Impares :"+analitic.getUnpaired());
		if (isQuantityNumberRowsEquals(analitic, dataAnalitic))
			throw new RuleException("Quantidade de numeros em uma linha e igual, sorteio: " + analitic.getConcurse());
		//TODO verificar pq nao esta populando
		//if (isAvgPairUnpairedEquals(analitic, dataAnalitic))
			//throw new RuleException("Quantidade de numeros em uma linha e igual, sorteio: " + dataAnalitic.getConcurse());

	}

	private boolean isAvgPairUnpairedEquals(RaffleDataAnalytic analitic, RaffleDataAnalytic dataAnalitic) {
		return analitic.getAvgPair().equals(dataAnalitic.getAvgPair()) && analitic.getAvgUnPaired().equals(dataAnalitic.getAvgUnPaired());
	}

	private boolean isQuantityNumberRowsEquals(RaffleDataAnalytic analitic, RaffleDataAnalytic dataAnalitic) {
		return analitic.getFirstRow().equals(dataAnalitic.getFirstRow()) && analitic.getSecondRow().equals(dataAnalitic.getSecondRow()) && analitic.getThirdRow().equals(dataAnalitic.getThirdRow()) && analitic.getFourthRow().equals(dataAnalitic.getFourthRow()) && analitic.getFivethRow().equals(dataAnalitic.getFivethRow());
	}

	private boolean isPairUnpairedEquals(RaffleDataAnalytic analitic, RaffleDataAnalytic dataAnalitic) {
		return analitic.getPair().equals(dataAnalitic.getPair()) && analitic.getUnpaired().equals(dataAnalitic.getUnpaired());
	}

	private List<RaffleDataAnalytic> getAnalysis(Integer concurses) {
		QRaffleDataAnalytic analytic = QRaffleDataAnalytic.raffleDataAnalytic;
		JPAQuery subQuery = new JPAQuery(entityManager);
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(analytic).where(analytic.concurse.gt(subQuery.from(analytic).singleResult(analytic.concurse.max()) - concurses)).list(analytic);
	}

	public Raffle getDefaultRaffle() {
		Raffle raffle = new Raffle();
		raffle.setBall1(1);
		raffle.setBall2(2);
		raffle.setBall3(3);
		raffle.setBall4(5);
		raffle.setBall5(6);
		raffle.setBall6(7);
		raffle.setBall7(9);
		raffle.setBall8(11);
		raffle.setBall9(13);
		raffle.setBall10(14);
		raffle.setBall11(15);
		raffle.setBall12(21);
		raffle.setBall13(22);
		raffle.setBall14(23);
		raffle.setBall15(24);
		return raffle;
	}
}
