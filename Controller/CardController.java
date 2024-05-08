package Controller;

import Model.User;

import java.util.List;

import Model.Board;
import Model.Card;
import Model.Lists;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CardController {
	
    @PersistenceContext(unitName = "TrelloPU")
    private EntityManager entityManager;
public Response createCard(@PathParam("username")String user,@PathParam("Listname") String Listname, @PathParam("cardDescription")String cardDescription) {
        User currentUser = usercontroller.getUserByUsername(user);
        if (currentUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User does not exist.").build();
        }
        Lists list = findListByName(Listname); 


        if (list == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("List does not exist.").build();
        }

        if (!list.getOwner().equals(currentUser) && !list.getCollaborators().contains(currentUser)) {
            return Response.status(Response.Status.FORBIDDEN).entity("User does not have permission to create a card in this list.").build();
        }
        Card card = new Card(cardDescription);
        card.setOwner(currentUser);
        card.setList(list);
        list.addCard(card);
        entityManager.persist(card);
    //    return Response.status(Response.Status.CREATED).entity(card).build();
        return Response.status(Response.Status.CREATED).entity("Card created successfullly").build();

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
	public Lists findListByName(String Listname) {
        try {
            return entityManager.createQuery("SELECT l FROM Lists l WHERE l.Listname = :Listname", Lists.class)
                                .setParameter("Listname", Listname)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if no list is found
        }
    }
}
