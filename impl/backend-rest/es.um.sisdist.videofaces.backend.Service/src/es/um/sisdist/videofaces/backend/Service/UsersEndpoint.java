package es.um.sisdist.videofaces.backend.Service;

import es.um.sisdist.videofaces.backend.Service.impl.AppLogicImpl;
import es.um.sisdist.videofaces.models.UserDTO;
import es.um.sisdist.videofaces.models.UserDTOUtils;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
public class UsersEndpoint
{
    private AppLogicImpl impl = AppLogicImpl.getInstance();

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserInfo(@PathParam("username") String username)
    {
        return UserDTOUtils.toDTO(impl.getUserByEmail(username).orElse(null));
    }
}
