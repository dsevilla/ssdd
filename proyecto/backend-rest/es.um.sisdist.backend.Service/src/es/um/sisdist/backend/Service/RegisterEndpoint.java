package es.um.sisdist.backend.Service;

import java.net.http.HttpHeaders;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import es.um.sisdist.backend.Service.impl.AppLogicImpl;
import es.um.sisdist.backend.dao.models.User;
import es.um.sisdist.models.UserDTO;
import es.um.sisdist.models.UserDTOUtils;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/register")
public class RegisterEndpoint {
    
    private AppLogicImpl impl = AppLogicImpl.getInstance();
    Handler ch = new ConsoleHandler();
    private static final Logger logger = Logger.getLogger(UsersEndpoint.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(UserDTO newUser, @Context HttpHeaders headers, @Context Request request){
        System.out.println("Recibida petición de register");
        logger.info("Recbida petición de register");
        if (newUser == null) {
            System.out.println("Error: el backend recibió un usuario vacío.");
            return Response.status(Status.NOT_ACCEPTABLE).build();         
        }

        System.out.println("endpoint: El usuario no está vacío");

        User user = UserDTOUtils.fromDTO(newUser);

        if (impl.userExists(user)) {
            System.out.println("Error: El usuario ya existe");
            return Response.status(Status.CONFLICT).build();   
        }

        System.out.println("El usuario no existe");

        if(impl.registerUser(user)){
            return Response.status(Status.CREATED)
            .entity("{\"id\": \"" + user.getId() + "\"}")
            .type(MediaType.APPLICATION_JSON)
            .build();
        }  
        else return Response.status(Status.NOT_MODIFIED).build(); 
    }
}
