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
//import javax.persistence.OneToMany;

@Entity
public class Card {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String description;
    
   // @OneToMany(mappedBy = "card")
    //private List<String> comments;
    
    @ManyToOne
    @JoinColumn(name = "list_id")
    private Lists list;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User Owner;
    
    @ManyToMany
    @JoinTable(
            name = "card_collaborators",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> collaborators = new ArrayList<>();
    
    
    public Card(String description) {
        this.description = description;
       // this.comments = new ArrayList<>();
    }
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return Owner;
	}

	public void setOwner(User owner) {
		Owner = owner;
	}

	public List<User> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<User> collaborators) {
		this.collaborators = collaborators;
	}

	public Lists getList() {
		return list;
	}

	public void setList(Lists list) {
		this.list = list;
	}
	
	/*public void addComment(String comment) {
        comments.add(comment);
    }*/

}