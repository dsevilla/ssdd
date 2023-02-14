package es.um.sisdist.backend.Service;

import es.um.sisdist.backend.Service.impl.AppLogicImpl;
import es.um.sisdist.models.UserDTO;
import es.um.sisdist.models.UserDTOUtils;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ping")
public class PingEndpoint
{
    private AppLogicImpl impl = AppLogicImpl.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean ping()
    {
        return impl.ping(1);
    }
}
