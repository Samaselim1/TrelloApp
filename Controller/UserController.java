package Controller;

import Model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Stateless
public class UserController {
	
	@PersistenceContext(unitName="TrelloPU")
    private EntityManager entityManager;
	
	public Response registerUser(User user) {
	    if (getUserByUsername(user.getUsername()) != null) {
	        return Response.ok("User already exists.").build();
	    }
	    entityManager.persist(user);
        return Response.ok("User registered successfully.").build();

	}
	
	public Response login(User user) {
	    if (getUserByUsername(user.getUsername()) != null) {
	        if (user.getPassword().equals(user.getPassword())) {
	            return Response.ok("User logged in successfully.").build();
	        } else {
	            return Response.status(Response.Status.UNAUTHORIZED).entity("Incorrect password.").build();
	        }
	    } else {
	        return Response.status(Response.Status.UNAUTHORIZED).entity("User does not exist.").build();
	    }
	}

	
	public Response update(User user) {
	    
	    if (getUserByUsername(user.getUsername()) != null) {
	    	user.setEmail(user.getEmail());
		    user.setPassword(user.getPassword());
		    
		    entityManager.merge(user);
		    return Response.ok("User information updated successfully.").build();
	    }
	    
        return Response.status(Response.Status.UNAUTHORIZED).entity("User does not exist.").build();
	}

	   private User getUserByUsername(String username) {
		    try {
		        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
		        query.setParameter("username", username);
		        return (User) query.getSingleResult();
		    } catch (NoResultException e) {
		        return null; 
		    }
		}

}