package com.koala.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.koala.entity.raffle.Raffle;
import com.koala.exceptions.RuleException;
import com.koala.service.BuildGameService;
import com.koala.service.RulesService;

@Stateless
@Path("/game")
public class LotoGameResource {

	@EJB
	private BuildGameService buildGameService;
	
	@Inject
	private RulesService rulesService;

	@GET
	@Path("random/{order}")
	@Produces("application/json")
	public Response buildRaffleGame(@PathParam("order") Boolean order) throws Exception {
		return Response.status(200).entity(buildGameService.randomRaffle()).build();
	}

	@GET
	@Path("latesWithRandom")
	@Produces("application/json")
	public Response buildlatesWithRandom() throws Exception {
		return Response.status(200).entity(buildGameService.buildGameWithLateNumbers()).build();
	}

	@GET
	@Path("moreNumber")
	@Produces("application/json")
	public Response buildMoreNumbersRaffle() throws Exception {
		return Response.status(200).entity(buildGameService.buildGameWithMoreNumbers()).build();
	}

	@GET
	@Path("lessNumber")
	@Produces("application/json")
	public Response buildLessNumbersRaffle() throws Exception {
		return Response.status(200).entity(buildGameService.buildGameWithLessNumbers()).build();
	}

	@GET
	@Path("randomWithoutNumber/{number}")
	@Produces("application/json")
	public Response buildRandomWithOutNumber(@PathParam("number") Integer number) throws Exception {
		return Response.status(200).entity(buildGameService.buildRandomWithOutNumber(number)).build();
	}

	@GET
	@Path("randomPairUnpaired/{number}")
	@Produces("application/json")
	public Response buildWithPairUnpaired(@PathParam("number") Integer number) throws Exception {
		if (number < 3 || number > 13)
			return Response.status(Status.PRECONDITION_FAILED).entity("Choose numbers beetwen 3 and 13 ").build();
		return Response.status(200).entity(buildGameService.buildWithPairUnpaired(number)).build();
	}

	@GET
	@Path("buildBasedLastRaffle")
	@Produces("application/json")
	public Response buildRandomWithOutNumber() throws Exception {
		return Response.status(200).entity(buildGameService.buildGameBasedLastRaffle()).build();
	}

	@GET
	@Path("buildBasedDozens/{firstDozen}/{thirdDozen}")
	@Produces("application/json")
	public Response buildBasedDozens(@PathParam("firstDozen") Integer firstDozen, @PathParam("thirdDozen") Integer thirdDozen) throws Exception {
		if (firstDozen < 4 || firstDozen > 8)
			return Response.status(Status.PRECONDITION_FAILED).entity("Choose 4 or 8 numbers ").build();
		if (thirdDozen < 2 || thirdDozen > 3)
			return Response.status(Status.PRECONDITION_FAILED).entity("Choose 4 or 8 numbers ").build();
		return Response.status(200).entity(buildGameService.buildGameByDozens(firstDozen, thirdDozen)).build();
	}
	
	@GET
	@Path("avgLastConcurses/{lastConcurses}")
	@Produces("application/json")
	public Response getAVGLastConcurses(@PathParam("lastConcurses") Integer lastConcurses) throws Exception {
		return Response.status(200).entity(buildGameService.getAVGPairsByConcurses(lastConcurses)).build();
	}
	
	
	//TODO Validar com um jogo feito pelo usuario
	@GET
	@Path("validator/{range}")
	@Produces("application/json")
	public Response validator(@PathParam("range") Integer range) throws RuleException {
		return null;
		//return Response.status(200).entity(rulesService.validator(rulesService.getDefaultRaffle(), range)).build();
	}
}
