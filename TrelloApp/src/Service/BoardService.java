package Service;

import Model.User;
import Model.Board;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BoardService {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public Board createBoard(User user, String boardName) {
        Board board = new Board(boardName);
        // Implementation
        return board;
    }

    public void inviteUser(User user, Board board) {
        // Implementation
    }

    public void deleteBoard(User user, Board board) {
        // Implementation
    }
}
