package com.koala.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entity.QRaffle;
import com.entity.Raffle;
import com.koala.view.QViewLateByNumber;
import com.koala.view.QViewNumberLessDrawn;
import com.koala.view.QViewNumberMoreDrawn;
import com.koala.view.ViewLateByNumber;
import com.koala.view.ViewNumberLessDrawn;
import com.koala.view.ViewNumberMoreDrawn;
import com.mysema.query.jpa.impl.JPAQuery;

@Stateless
public class HistoricService {

	private static final String XML = "xml";

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@EJB
	private ImportService lotoImportService;

	@EJB
	private BuildGameService lotoBuildGameService;
	
	@EJB
	private AnalyticService analyticService;

	public List<Raffle> listAllRaffles() {
		QRaffle raffle = QRaffle.raffle;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(raffle).orderBy(raffle.concurse.asc()).list(raffle);
	}

	public void importHistoryRaffles() throws Exception {
		Raffle lastRaffle = lotoImportService.getData(XML);
		int lastRafleDatabase = getLastIndexRaffle();
		while (lastRafleDatabase != lastRaffle.getConcurse()) {
			lastRafleDatabase = lastRafleDatabase + 1;
			Raffle raffle = lotoImportService.getData(XML, lastRafleDatabase);
			if (this.findById(raffle.getConcurse()) == null) {
				entityManager.persist(analyticService.getRaffleDataAnality(raffle));
				entityManager.persist(raffle);
			}
		}
	}

	public Raffle findById(Integer id) {
		return entityManager.find(Raffle.class, id);
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

	public Integer getLastIndexRaffle() {
		QRaffle raffle = QRaffle.raffle;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(raffle).singleResult(raffle.concurse.max());
	}
	
	public Raffle getLastRaffle() {
		QRaffle raffle = QRaffle.raffle;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(raffle).limit(1).orderBy(raffle.concurse.asc()).singleResult(raffle);
	}

}
