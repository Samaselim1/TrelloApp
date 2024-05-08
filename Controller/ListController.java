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

public Response deleteList(@PathParam("listName") String Listname, @PathParam("user") String user) {
		    User requestingUser = usercontroller.getUserByUsername(user);
		    if (requestingUser == null) {
		        return Response.status(Response.Status.NOT_FOUND).entity("User does not exist.").build();
		    }

		    Lists list = listcontroller.findListByName(Listname);
		    if (list == null) {
		        return Response.status(Response.Status.NOT_FOUND).entity("List does not exist.").build();
		    }

		    if (!list.getOwner().equals(requestingUser) && !list.getCollaborators().contains(requestingUser)) {
		        return Response.status(Response.Status.FORBIDDEN).entity("User does not have permission to delete this list.").build();
		    }

		    entityManager.remove(list);
		    entityManager.flush();  // Ensure the removal is persisted immediately
		    return Response.status(Response.Status.OK).entity("List deleted successfully.").build();
		}
	    

	
}
