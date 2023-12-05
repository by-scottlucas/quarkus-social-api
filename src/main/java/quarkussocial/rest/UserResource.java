package quarkussocial.rest;

import dto.CreateUserRequest;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quarkussocial.domain.model.User;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource{

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest){
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        user.persist();

        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers(){
        return Response.ok().build();
    }

}