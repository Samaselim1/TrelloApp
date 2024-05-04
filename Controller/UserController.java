package Controller;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Service.UserService;
import Model.User; 
@Path("/users")
public class UserController {
	
	@Inject
    private UserService userService;
	
	
	@POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        userService.registerUser(user);
        return Response.ok("User registered Successfully").build();
    }
    
//    @POST
//    @Path("/register")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response registerUser(User user) {
//    	UserService us =new UserService();
//    	us.registerUser(user);
//        return Response.ok().build();
//    }
    
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(User credentials) {
        UserService userService = new UserService();
        boolean authenticated = userService.login(credentials);
        if (authenticated) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        UserService userService = new UserService();
        userService.update(user);
        return Response.ok().build();
    }
}
