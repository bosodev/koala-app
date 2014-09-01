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
import javax.ws.rs.core.Response.Status;

import com.koala.service.LotoBuildGameService;

@Stateless
@Path("/game")
public class LotoGameResource {

	@EJB
	private LotoBuildGameService lotoBuildGameService;

	@GET
	@Path("random/{order}")
	@Produces("application/json")
	public Response buildRaffleGame(@PathParam("order") Boolean order) throws IOException, ParseException {
		return Response.status(200).entity(lotoBuildGameService.randomRaffle()).build();
	}

	@GET
	@Path("latesWithRandom")
	@Produces("application/json")
	public Response buildlatesWithRandom() throws IOException, ParseException {
		return Response.status(200).entity(lotoBuildGameService.buildGameWithLateNumbers()).build();
	}

	@GET
	@Path("moreNumber")
	@Produces("application/json")
	public Response buildMoreNumbersRaffle() throws IOException, ParseException {
		return Response.status(200).entity(lotoBuildGameService.buildGameWithMoreNumbers()).build();
	}

	@GET
	@Path("lessNumber")
	@Produces("application/json")
	public Response buildLessNumbersRaffle() throws IOException, ParseException {
		return Response.status(200).entity(lotoBuildGameService.buildGameWithLessNumbers()).build();
	}

	@GET
	@Path("randomWithoutNumber/{number}")
	@Produces("application/json")
	public Response buildRandomWithOutNumber(@PathParam("number") Integer number) throws IOException, ParseException {
		return Response.status(200).entity(lotoBuildGameService.buildRandomWithOutNumber(number)).build();
	}

	@GET
	@Path("randomPairUnpaired/{number}")
	@Produces("application/json")
	public Response buildWithPairUnpaired(@PathParam("number") Integer number) throws IOException, ParseException {
		if (number < 3 || number > 13)
			return Response.status(Status.PRECONDITION_FAILED).entity("Choose numbers beetwen 3 and 13 ").build();
		return Response.status(200).entity(lotoBuildGameService.buildWithPairUnpaired(number)).build();
	}

	@GET
	@Path("buildBasedLastRaffle")
	@Produces("application/json")
	public Response buildRandomWithOutNumber() throws IOException, ParseException {
		return Response.status(200).entity(lotoBuildGameService.buildGameBasedLastRaffle()).build();
	}

	@GET
	@Path("buildBasedDozens/{firstDozen}/{thirdDozen}")
	@Produces("application/json")
	public Response buildBasedDozens(@PathParam("firstDozen") Integer firstDozen, @PathParam("thirdDozen") Integer thirdDozen) throws IOException, ParseException {
		if (firstDozen < 4 || firstDozen > 8)
			return Response.status(Status.PRECONDITION_FAILED).entity("Choose 4 or 8 numbers ").build();
		if (thirdDozen < 2 || thirdDozen > 3)
			return Response.status(Status.PRECONDITION_FAILED).entity("Choose 4 or 8 numbers ").build();
		return Response.status(200).entity(lotoBuildGameService.buildGameByDozens(firstDozen, thirdDozen)).build();
	}
	
	@GET
	@Path("avgLastConcurses/{lastConcurses}")
	@Produces("application/json")
	public Response getAVGLastConcurses(@PathParam("lastConcurses") Integer lastConcurses) {
		return Response.status(200).entity(lotoBuildGameService.getAVGPairsByConcurses(lastConcurses)).build();
	}
}
