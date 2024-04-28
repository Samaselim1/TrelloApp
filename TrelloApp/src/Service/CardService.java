package Service;

import Model.User;

import java.util.List;

import Model.Board;
import Model.Card;
import Model.Lists;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CardService {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	 public Card createCard(User user, Lists list, String cardDescription) {
	        Card card = new Card(cardDescription);
	        list.addCard(card);
	        // Implementation
	        return card;
	    }

	    public void moveCard(User user, Card card, List newList) {
	        // Implementation
	        // Remove card from current list and add to new list
	        // Check user role to ensure only appropriate transitions are allowed
	    }

	    public void assignCard(User user, Card card, User assignee) {
	        // Implementation
	    }

	    public void addComment(User user, Card card, String comment) {
	        card.addComment(comment);
	        // Implementation
	    }
}
