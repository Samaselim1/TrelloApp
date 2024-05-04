package Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 private String username;
	 private String password;
	 private String email;
	 private String role;
	 
	 @ManyToMany(mappedBy = "collaborators")
	    private List<Board> boards = new ArrayList<>();

	 @OneToMany(mappedBy = "owner")
	    private List<Board> ownedBoards= new ArrayList<>();
	 
	 public User( ) {};
	 public User(String email, String password, String name) {
	        this.email = email;
	        this.password = password;
	        this.username = name;

	    }
	
	 public void setId(Long id)
	 {
		 this.id = id;
	 }
	 
	 public Long getId()
	 {
		 return id;
	 }
	 
	 public String getUsername() {
		 return username;
	 }
	 public void setUsername(String name) {
		 this.username = name;
	 }
	 
	 public String getPassword() {
		 return password;
	 }
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 
	 public void setEmail(String email) {
		 this.email = email;
	 }
	 
	public String getEmail() {
		return email;
	}
	
	public List<Board> getOwnedBoards() {
        return ownedBoards;
    }

    public void setOwnedBoards(List<Board> ownedBoards) {
        this.ownedBoards = ownedBoards;
    }
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	 public List<Board> getBoards() {
	        return boards;
	    }

	    public void setBoards(List<Board> boards) {
	        this.boards = boards;
	    }
}
