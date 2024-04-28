package Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lists {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	  private String Listname;
	  @ManyToOne
	    private Board board;

	    @OneToMany(mappedBy = "taskList")
	    private List<Card> cards;

	    public Lists(String name) {
	        this.Listname = name;
	        this.cards = new ArrayList<>();
	    }

	    public void addCard(Card card) {
	        cards.add(card);
	    }

	    public void removeCard(Card card) {
	        cards.remove(card);
	    }

}
