package Service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Controller.BoardController;
import Model.Board;
import Model.User;


@Path("/boards")
public class BoardService {


    @Inject
    private BoardController boardcontroller;

@POST
    @Path("/create/{boardName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBoard(User user, @PathParam("boardName")String boardName) {
        return boardcontroller.createBoard(user, boardName);
    }
  @POST
    @Path("/invite/{username}/{boardName}/{username2}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inviteUser(@PathParam("username")String user,@PathParam("boardName")String board, @PathParam("username2")String invitee) {
    	return boardcontroller.inviteUser(user, board , invitee);
    }
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBoard(User user, Board board) {
        return boardcontroller.deleteBoard(user, board);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBoards(User user) {
        List<Board> boards = boardcontroller.getAllBoards(user);
        return Response.ok().entity(boards).build();
    }
}
