package Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Board {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String Boardname;
    
    @OneToMany(mappedBy = "board")
    private List<List> lists;

    public Board(String name) {
        this.Boardname = name;
        this.lists = new ArrayList<>();
    }
    
	public String getBoardname() {
		return Boardname;
	}

	public void setBoardname(String boardname) {
		Boardname = boardname;
	}
	
	public void addList(List list) {
        lists.add(list);
    }

}
