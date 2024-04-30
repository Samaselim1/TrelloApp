package Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lists {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	  private String Listname;

	  @ManyToOne
	  @JoinColumn(name = "board_id")
	  private Board board;

	  @ManyToOne
	  @JoinColumn(name = "owner_id")
	  private User owner; 

	  @ManyToMany
	  @JoinTable(
	           name = "list_collaborators",
	           joinColumns = @JoinColumn(name = "list_id"),
	           inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	    private List<User> collaborators = new ArrayList<>();
	  
	    @OneToMany(mappedBy = "list")
	    private List<Card> cards;

	    public Lists(String listName, Board board, User owner) {
	        this.Listname = listName;
	        this.board = board;
	        this.owner = owner;
	        this.cards = new ArrayList<>();
	    }

		public String getListname() {
			return Listname;
		}

		public void setListname(String listname) {
			Listname = listname;
		}

		public User getOwner() {
			return owner;
		}

		public void setOwner(User owner) {
			this.owner = owner;
		}

		public List<User> getCollaborators() {
			return collaborators;
		}

		public void setCollaborators(List<User> collaborators) {
			this.collaborators = collaborators;
		}
		
		public void addCard(Card card) {
	        cards.add(card);
	    }

	    public void removeCard(Card card) {
	        cards.remove(card);
	    }

}
