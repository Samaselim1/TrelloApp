package Service;

import Model.User;
import Model.Board;
import Model.Lists;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ListService {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	 public Lists createList(User user, Board board, String listName) {
	        // Implementation
	        return null;
	    }

	    public void deleteList(User user, Lists list) {
	        // Implementation
	    }
}
