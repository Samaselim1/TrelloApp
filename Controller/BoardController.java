package Controller;

import Model.User;
import Model.Board;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.Response;

import java.util.List;

@Stateless
public class BoardController {
	
	@PersistenceContext(unitName="TrelloPU")
    private EntityManager entityManager;
	
public Response createBoard(User user, @PathParam("boardName")String boardName) 
	{
		User owner = usercontroller.getUserByUsername(user.getUsername());
		if (owner == null) {
	    		 return Response.status(Response.Status.NOT_FOUND).entity("User does not existtttt.").build();
	    } 
		if (boardName == null || boardName.trim().isEmpty()) {
		    return Response.status(Response.Status.BAD_REQUEST).entity("Board name is required.").build();
		}

		else if (!owner.getRole().equals("Team Leader")) {
	        return Response.status(Response.Status.FORBIDDEN).entity("Only team leaders can create boards").build();
	    } else {
	    	Board board = new Board(boardName, owner);
	    	board.setOwner(owner);
	    	owner.getOwnedBoards().add(board); 
	    	entityManager.persist(board);
	    	return Response.status(Response.Status.CREATED).entity("Board created successfully").build();
	    }
	}
    public void inviteUser(User user, Board board) {
        board.getCollaborators().add(user); 
        entityManager.merge(board); 
    }

    public Response deleteBoard(User user, Board board) {
        if (user.equals(board.getOwner())) {
            entityManager.remove(board);
            return Response.ok("Board deleted successfully").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User does not have permission to delete the board").build();
        }
    }

    public List<Board> getAllBoards(User user) {
        Query query = entityManager.createQuery("SELECT b FROM Board b WHERE b.owner = :user OR :user MEMBER OF b.collaborators");
        query.setParameter("user", user);
        return query.getResultList();
    }
}
