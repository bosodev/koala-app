package com.koala.resources;

import java.io.IOException;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.koala.service.HistoricService;

@Stateless
@Path("/historic")
public class HistoricResource {

	@EJB
	private HistoricService historicService;

	@GET
	@Path("allRaffles")
	@Produces("application/json")
	public Response allRaffles() throws IOException, ParseException {
		return Response.status(200).entity(historicService.listAllRaffles()).build();
	}

	@GET
	@Path("lateNumbers/{order}")
	@Produces("application/json")
	public Response listLateNumbers(@PathParam("order") Boolean order) throws IOException, ParseException {
		return Response.status(200).entity(historicService.listLateNumbers(order)).build();
	}

	@GET
	@Path("numbersLessDrawn")
	@Produces("application/json")
	public Response listNumbersLessDrawn() throws IOException, ParseException {
		return Response.status(200).entity(historicService.listNumbersLessDrawn()).build();
	}

	@GET
	@Path("numbersMoreDrawn")
	@Produces("application/json")
	public Response listNumbersMoreDrawn() throws IOException, ParseException {
		return Response.status(200).entity(historicService.listNumbersMoreDrawn()).build();
	}

	@GET
	@Path("lastRaffle")
	@Produces("application/json")
	public Response lastRaffle() throws IOException, ParseException {
		return Response.status(200).entity(historicService.getLastRaffle()).build();
	}

}
