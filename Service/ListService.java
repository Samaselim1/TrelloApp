package Service;

import Model.User;
import Model.Board;
import Model.Lists;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ListService {
	
    @PersistenceContext(unitName = "TrelloPU")
    private EntityManager entityManager;
	
	 public Lists createList(User user, Board board, String listName) {
		 Lists list = new Lists(listName, board, user);
		 list.setOwner(user);
		 entityManager.persist(list);
	        return list;
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
