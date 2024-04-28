package Service;

import Model.User;
import Model.Board;

import javax.ejb.Stateless;

@Stateless
public class TeamLeaderService {

	 public Board createBoard(User teamLeader, String boardName) {
	        // Implementation
	        return null;
	    }

	    public void inviteUser(User teamLeader, Board board, User collaborator) {
	        // Implementation
	    }

	    public void deleteBoard(User teamLeader, Board board) {
	        // Implementation
	    }
}
