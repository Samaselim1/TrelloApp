package Service;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Controller.ListController;
import Model.Board;
import Model.User;
import Model.Lists;


@Path("/lists")
public class ListService {

    @Inject
    private ListController listcontroller;

    @PersistenceContext(unitName = "TrelloPU")
    private EntityManager entityManager;
    
    @POST
    @Path("/create/{listName}/{boardName}/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	 public Response createList(@PathParam("listName")String listName, @PathParam("boardName") String boardName,@PathParam("user") String user) {
        try {
            return listcontroller.createList(listName, boardName, user);
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
            listcontroller.deleteList(user, list);
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
            List<Lists> lists = listcontroller.getAllLists(board);
            return Response.ok().entity(lists).build();
        }

}
