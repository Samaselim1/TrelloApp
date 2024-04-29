package Controller;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ejb.User; 
@Path("/users")
public class UserController {
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
    	UserService us =new UserService();
    	us.register(user);
        return Response.ok().build();
    }
}
