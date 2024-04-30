package Controller;

import Model.Board;
import Model.Lists;
import Model.User;
import Service.ListService;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/list")
public class ListController {

    @EJB
    private ListService listService;

    @PersistenceContext(unitName = "TrelloPU")
    private EntityManager entityManager;
    
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createList(User user, Board board, String listName) {
        try {
            Lists createdList = listService.createList(user, board, listName);
            return Response.status(Response.Status.CREATED).entity(createdList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to create list").build();
        }
    }

    @DELETE
    @Path("/delete/{listId}")
    public Response deleteList(User user, @PathParam("listId") Long listId) {
        try {
            Lists list = entityManager.find(Lists.class, listId);
            if (list == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("List not found").build();
            }
            listService.deleteList(user, list);
            return Response.status(Response.Status.OK).entity("List deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete list").build();
        }
    }
    

        @GET
        @Path("/all/{boardId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllLists(@PathParam("boardId") Long boardId) {
            Board board = entityManager.find(Board.class, boardId);
            if (board == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Board not found").build();
            }
            List<Lists> lists = listService.getAllLists(board);
            return Response.ok().entity(lists).build();
        }

}
