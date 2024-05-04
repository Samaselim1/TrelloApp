package Service;

import Model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserService {
	
	@PersistenceContext(unitName="TrelloPU")
    private EntityManager entityManager;
	
	   public void registerUser(User user) {
	         if (getUserByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }
        entityManager.persist(user);
	    }

	   public boolean login(User credentials) {
	        User user = getUserByUsername(credentials.getUsername());
	        // Check if user exists and password matches
	        return user != null && user.getPassword().equals(credentials.getPassword());
	    }

	   public void update(User user) {
           User existingUser = getUserByUsername(user.getUsername());
   // Check if user exists
   if (existingUser == null) {
       throw new RuntimeException("User does not exist");
   }
   // Update user information
   existingUser.setUsername(user.getUsername());
   existingUser.setEmail(user.getEmail());
   existingUser.setPassword(user.getPassword());
}

	private User getUserByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
