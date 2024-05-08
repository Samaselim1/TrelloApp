package Controller;

import Model.User;
import Model.Board;
import Model.Lists;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ListController {
	
    @PersistenceContext(unitName = "TrelloPU")
    private EntityManager entityManager;
	
	public Response createList(@PathParam("listName")String listName, @PathParam("boardName") String boardName,@PathParam("user") String user) {
			User owner = usercontroller.getUserByUsername(user);
		 if (owner == null) 
		 {
    		 return Response.status(Response.Status.NOT_FOUND).entity("User does not existt.").build();
    } 
		    Board boardn = boardcontroller.getBoardByName(boardName);
		    if (boardn == null) {
		        return Response.status(Response.Status.NOT_FOUND).entity("Board does not exist.").build();
		    }
		    if (!boardn.getOwner().equals(owner)) {
		        return Response.status(Response.Status.FORBIDDEN).entity("User does not have access to the specified board.").build();
		    }
		   Lists list = new Lists();
		    list.setOwner(owner); // Set the owner of the list to be the user
		    list.setListname(listName);
		    list.setBoard(boardn);
		    entityManager.persist(list);

		    return Response.status(Response.Status.CREATED).entity("List created successfully.").build();
		}

	 public void deleteList(User user, Lists list) {
	        if (user.equals(list.getOwner())) {
	            entityManager.remove(entityManager.merge(list));
	        } else {
	            throw new RuntimeException("Only TeamLeader can delete lists.");
	        }
	    }
	    
	    public List<Lists> getAllLists(Board board) {
	        Query query = entityManager.createQuery("SELECT l FROM Lists l WHERE l.board = :board");
	        query.setParameter("board", board);
	        return query.getResultList();
	    }
}
