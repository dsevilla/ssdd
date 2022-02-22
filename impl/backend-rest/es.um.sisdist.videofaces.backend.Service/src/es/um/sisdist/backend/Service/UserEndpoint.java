package es.um.sisdist.backend.Service;

import java.net.URI;
import java.net.URISyntaxException;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// POJO, no interface no extends

@Path("/user")
public class UserEndpoint
{
    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello()
    {
        return "Hello Jersey";
    }

}
