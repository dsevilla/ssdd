package es.um.sisdist.backend.Service;

import java.util.logging.Logger;

import es.um.sisdist.backend.Service.impl.AppLogicImpl;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/ping")
public class PingEndpoint
{
    private AppLogicImpl impl = AppLogicImpl.getInstance();
    private static final Logger logger = Logger.getLogger(UsersEndpoint.class.getName());

    @GET
    public Response ping()
    {
        System.out.println("ping recibido");
        logger.info("ping recibido logger");
        return Response.ok(impl.ping(1)).build();
    }
}
