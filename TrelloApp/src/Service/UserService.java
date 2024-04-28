package Service;

import Model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	   public void registerUser(String email, String password) {
	        // Implementation
	    }

	    public User loginUser(String email, String password) {
	        // Implementation
	        return null;
	    }

	    public void updateProfile(User user) {
	        // Implementation
	    }
}
