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

@Stateless
@Path("/raffle")
public class RaffleResource {

	@EJB
	private HistoricService histService;

	@GET
	@Path("list")
	@Produces("application/json")
	public Response importDataRaffle() throws IOException, ParseException {
		return Response.status(200).entity(histService.listAllRaffles()).build();
	}

	@GET
	@Path("importdatabase")
	@Produces("application/json")
	public Response saveRaffles() throws Exception {
		histService.importHistoryRaffles();
		return Response.status(200).entity("Import historic to database successful...").build();
	}
}