package com.koala.resources;

import java.io.IOException;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.koala.service.HistoricService;
import com.koala.service.LotoImportService;

@Stateless
@Path("/raffle")
public class RaffleResource {

	@EJB
	private HistoricService histService;

	@EJB
	private LotoImportService lotoImportService;

	@GET
	@Path("list")
	@Produces("application/json")
	public Response importDataRaffle() throws IOException, ParseException {
		return Response.status(200).entity(lotoImportService.readHtmlFile()).build();
	}

	@GET
	@Path("importdatabase")
	@Produces("application/json")
	public Response saveRaffles() throws IOException, ParseException {
		histService.importHistoryRaffles();
		return Response.status(200).entity("Import historic to database successful...").build();
	}
}