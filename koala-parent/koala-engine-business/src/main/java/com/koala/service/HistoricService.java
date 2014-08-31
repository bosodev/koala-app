package com.koala.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.koala.entity.QRaffle;
import com.koala.entity.Raffle;
import com.koala.entity.RaffleDataAnalytic;
import com.koala.views.QViewLateByNumber;
import com.koala.views.QViewNumberLessDrawn;
import com.koala.views.QViewNumberMoreDrawn;
import com.koala.views.ViewLateByNumber;
import com.koala.views.ViewNumberLessDrawn;
import com.koala.views.ViewNumberMoreDrawn;
import com.mysema.query.jpa.impl.JPAQuery;

@Stateless
public class HistoricService {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@EJB
	private LotoImportService lotoImportService;

	@EJB
	private RaffleDataAnalyticService raffleDataAnalyticService;

	public void importHistoryRaffles() throws IOException, ParseException {
		List<Raffle> raffles = (List<Raffle>) lotoImportService.readHtmlFile();
		for (Raffle raffle : raffles) {
			if (this.findById(raffle.getConcurse()) == null) {
				entityManager.persist(this.getRaffleDataAnality(raffle));
				entityManager.persist(raffle);
			}
		}
	}

	public Raffle findById(Integer id) {
		return entityManager.find(Raffle.class, id);
	}

	public RaffleDataAnalytic getRaffleDataAnality(Raffle raffle) {
		RaffleDataAnalytic raffleData = new RaffleDataAnalytic();
		raffleData.setConcurse(raffle.getConcurse());
		raffleData.setAverage(raffleDataAnalyticService.averageNumbers(raffle));
		raffleData.setSum(raffleDataAnalyticService.sumNumbers(raffle));
		Map<String, Integer> pairUnpaired = raffleDataAnalyticService.getPairInteger(raffle);
		setTypeNumber(raffleData, pairUnpaired);
		raffleData.setGreaterSequence(raffleDataAnalyticService.getGreaterSequence(raffle));
		setSumRows(raffle, raffleData);
		setTotalNumbersRow(raffle, raffleData);
		return raffleData;
	}

	private void setTypeNumber(RaffleDataAnalytic raffleData, Map<String, Integer> pairUnpaired) {
		raffleData.setPair(pairUnpaired.get("pair"));
		raffleData.setUnpaired(pairUnpaired.get("unpaired"));
	}

	private void setSumRows(Raffle raffle, RaffleDataAnalytic raffleData) {
		raffleData.setSumFirstRow(raffleDataAnalyticService.rowSumTotal(1, 5, raffle, true));
		raffleData.setSumSecondRow(raffleDataAnalyticService.rowSumTotal(5, 10, raffle, true));
		raffleData.setSumThirdRow(raffleDataAnalyticService.rowSumTotal(11, 15, raffle, true));
		raffleData.setSumFourthRow(raffleDataAnalyticService.rowSumTotal(16, 20, raffle, true));
		raffleData.setSumFivethRow(raffleDataAnalyticService.rowSumTotal(21, 25, raffle, true));
	}

	private void setTotalNumbersRow(Raffle raffle, RaffleDataAnalytic raffleData) {
		raffleData.setFirstRow(raffleDataAnalyticService.rowSumTotal(1, 5, raffle, false));
		raffleData.setSecondRow(raffleDataAnalyticService.rowSumTotal(5, 10, raffle, false));
		raffleData.setThirdRow(raffleDataAnalyticService.rowSumTotal(11, 15, raffle, false));
		raffleData.setFourthRow(raffleDataAnalyticService.rowSumTotal(16, 20, raffle, false));
		raffleData.setFivethRow(raffleDataAnalyticService.rowSumTotal(21, 25, raffle, false));
	}

	public List<ViewLateByNumber> listLateNumbers(Boolean asc) {
		QViewLateByNumber total = QViewLateByNumber.viewLateByNumber;
		JPAQuery query = new JPAQuery(entityManager);
		if (asc)
			return query.from(total).orderBy(total.total.asc()).list(total);
		else
			return query.from(total).orderBy(total.total.desc()).list(total);
	}

	public List<ViewNumberLessDrawn> listNumbersLessDrawn() {
		QViewNumberLessDrawn total = QViewNumberLessDrawn.viewNumberLessDrawn;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(total).orderBy(total.total.asc()).list(total);
	}

	public List<ViewNumberMoreDrawn> listNumbersMoreDrawn() {
		QViewNumberMoreDrawn total = QViewNumberMoreDrawn.viewNumberMoreDrawn;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(total).orderBy(total.total.asc()).list(total);
	}

	public Raffle getLastRaffle() {
		QRaffle raffle = QRaffle.raffle;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(raffle).limit(1).orderBy(raffle.concurse.asc()).singleResult(raffle);
	}

}
