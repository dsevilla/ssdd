package es.um.sisdist.backend.Service;

import es.um.sisdist.backend.Service.impl.AppLogicImpl;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/ping")
public class PingEndpoint
{
    private AppLogicImpl impl = AppLogicImpl.getInstance();

    @GET
    public Response ping()
    {
        return Response.ok(impl.ping(1)).build();
    }
}
