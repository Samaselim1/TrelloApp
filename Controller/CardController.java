package Controller;

import                  Model.User;
import Model.Card;
import Model.Lists;
import Service.CardService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cards")
public class CardController {

    @Inject
    private CardService cardService;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCard(User user, Lists list, String cardDescription) {
        Card card = cardService.createCard(user, list, cardDescription);
        return Response.ok().entity(card).build();
    }

    @PUT
    @Path("/move")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response moveCard(User user, Card card, Lists newList) {
        cardService.moveCard(user, card, newList);
        return Response.ok().build();
    }

    @PUT
    @Path("/assign")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignCard(User user, Card card, User assignee) {
        cardService.assignCard(user, card, assignee);
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
