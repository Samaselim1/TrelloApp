package Service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Controller.CardController;
import Model.Card;
import Model.Lists;
import Model.User;


@Path("/cards")
public class CardService {

	    @Inject
	    private CardController cardcontroller;

	@POST
	    @Path("/create/{username}/{listName}/{cardDescription}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response createCard(@PathParam("username")String username,@PathParam("listName") String listName, @PathParam("cardDescription")String cardDescription) {
	        return cardcontroller.createCard(username, listName, cardDescription);
	      	    }

	    @PUT
	    @Path("/move")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response moveCard(User user, Card card, Lists newList) {
	    	cardcontroller.moveCard(user, card, newList);
	        return Response.ok().build();
	    }

	    @PUT
	    @Path("/assign")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response assignCard(User user, Card card, User assignee) {
	    	cardcontroller.assignCard(user, card, assignee);
	        return Response.ok().build();
	    }

	    /* public Response addComment(User user, Card card, String comment) {
	    @PUT
	    @Path("/comment")
	    @Consumes(MediaType.APPLICATION_JSON)
	        cardService.addComment(user, card, comment);
	        return Response.ok().build();
	    }*/
}
