package Controller;

import Model.User;
import Model.Lists;
import Model.Card;

import javax.ejb.Stateless;

@Stateless
public class CollaboratorController {

	  public void moveCard(User collaborator, Lists currentList, Lists newList) {
	        // Implementation
	    }

	    public void assignCard(User collaborator, Card card, User assignee) {
	        // Implementation
	    }

	    public void addComment(User collaborator, Card card, String comment) {
	        // Implementation
	    }
}