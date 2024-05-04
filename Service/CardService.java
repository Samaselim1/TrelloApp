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
	
    @PersistenceContext(unitName = "TrelloPU")
    private EntityManager entityManager;
	
    public Card createCard(User user, Lists list, String cardDescription) {
        if (list.getCollaborators().contains(user)) {
            Card card = new Card(cardDescription);
            list.addCard(card);
            entityManager.persist(card);
            return card;
        } else {
            throw new RuntimeException("User does not have permission to create a card in this list.");
        }
    }

    public void moveCard(User user, Card card, Lists newList) {
        if (card.getList().getCollaborators().contains(user)) {
            Lists currentList = card.getList();
            currentList.removeCard(card);
            newList.addCard(card);
            card.setList(newList);
            entityManager.merge(card);
        } else {
            throw new RuntimeException("User does not have permission to move the card.");
        }
    }

    public void assignCard(User user, Card card, User assignee) {
        if (card.getList().getCollaborators().contains(user)) {
            card.setOwner(assignee);
            entityManager.merge(card);
        } else {
            throw new RuntimeException("User does not have permission to assign the card.");
        }
    }
    
   /* public void addComment(User user, Card card, String comment) {
        if (card.getList().getCollaborators().contains(user)) {
            card.addComment(comment);
            entityManager.merge(card);
        } else {
            throw new RuntimeException("User does not have permission to add a comment to the card.");
        }
    }*/
}
