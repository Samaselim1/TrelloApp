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
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBoard(User user, String boardName) {
        return boardcontroller.createBoard(user, boardName);
    }

    @POST
    @Path("/invite")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inviteUser(User user, Board board) {
    	boardcontroller.inviteUser(user, board);
        return Response.ok().build();
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
