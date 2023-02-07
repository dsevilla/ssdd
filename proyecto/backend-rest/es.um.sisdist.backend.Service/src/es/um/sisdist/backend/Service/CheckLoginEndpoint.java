package es.um.sisdist.backend.Service;

import java.util.Optional;

import es.um.sisdist.backend.Service.impl.AppLogicImpl;
import es.um.sisdist.models.UserDTO;
import es.um.sisdist.models.UserDTOUtils;
import es.um.sisdist.backend.dao.models.User;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

// POJO, no interface no extends

@Path("/checkLogin")
public class CheckLoginEndpoint
{
    private AppLogicImpl impl = AppLogicImpl.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkUser(UserDTO uo)
    {
        Optional<User> u = impl.checkLogin(uo.getEmail(), uo.getPassword());
        if (u.isPresent())
            return Response.ok(UserDTOUtils.toDTO(u.get())).build();
        else
            return Response.status(Status.FORBIDDEN).build();
    }
}
