package com.koala.schedule;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import lombok.extern.log4j.Log4j;

import com.koala.service.HistoricService;

@Log4j
@Stateless
public class ImportRaffleJob {

	@EJB
	private HistoricService historicService;

	@Schedule(minute = "*/1", second = "*", persistent = false)
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void execute() throws Exception {
		log.info("executing schedule import raffle...");
		historicService.importHistoryRaffles();
	}
}
