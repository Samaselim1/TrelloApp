package Service;

import Model.User;
import Model.Board;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class BoardService {
	
	@PersistenceContext(unitName="TrelloPU")
    private EntityManager entityManager;
	
	public Board createBoard(User user, String boardName) {
        Board board = new Board(boardName);
        board.setOwner(user); 
        entityManager.persist(board); 
        return board;
    }

    public void inviteUser(User user, Board board) {
        board.getCollaborators().add(user); 
        entityManager.merge(board); 
    }

    public void deleteBoard(User user, Board board) {
        if (user.equals(board.getOwner())) {
            entityManager.remove(board); 
        } else {
            throw new RuntimeException("User does not have permission to delete the board");
        }
    }

    public List<Board> getAllBoards(User user) {
        Query query = entityManager.createQuery("SELECT b FROM Board b WHERE b.owner = :user OR :user MEMBER OF b.collaborators");
        query.setParameter("user", user);
        return query.getResultList();
    }
}
