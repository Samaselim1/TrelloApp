package Service;

import Model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {
	
	@PersistenceContext(unitName="TrelloPU")
    private EntityManager entityManager;
	
	   public void registerUser(User user) {
	         if (getUserByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }
        entitManager.persist(user);
	    }

	    public User loginUser(String email, String password) {
	        // Implementation
	        return null;
	    }

	    public void updateProfile(User user) {
	        // Implementation
	    }
	 private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
