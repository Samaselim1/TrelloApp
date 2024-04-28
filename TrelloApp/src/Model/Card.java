package Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Card {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String description;
    private List<String> comments;
    
    @ManyToOne
    private Lists taskList;
    
    public Card(String description) {
        this.description = description;
        this.comments = new ArrayList<>();
    }
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addComment(String comment) {
        comments.add(comment);
    }

}
