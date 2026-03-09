package es.um.sisdist.backend.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import es.um.sisdist.backend.grpc.GrpcServiceGrpc;
import es.um.sisdist.backend.grpc.PingRequest;
import es.um.sisdist.models.UserDTO;
import es.um.sisdist.models.UserDTOUtils;
import es.um.sisdist.backend.dao.DAOFactoryImpl;
import es.um.sisdist.backend.dao.IDAOFactory;
import es.um.sisdist.backend.dao.models.Chat;
import es.um.sisdist.backend.dao.models.User;
import es.um.sisdist.backend.dao.models.utils.UserUtils;
import es.um.sisdist.backend.dao.user.IUserDAO;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.ws.rs.core.Response;

/**
 * @author dsevilla
 *
 */
public class AppLogicImpl
{
    IDAOFactory daoFactory;
    IUserDAO dao;

    private static final Logger logger = Logger.getLogger(AppLogicImpl.class.getName());

    private final ManagedChannel channel;
    private final GrpcServiceGrpc.GrpcServiceBlockingStub blockingStub;
    //private final GrpcServiceGrpc.GrpcServiceStub asyncStub;

    static AppLogicImpl instance = new AppLogicImpl();

    private AppLogicImpl()
    {
        daoFactory = new DAOFactoryImpl();
        Optional<String> backend = Optional.ofNullable(System.getenv("DB_BACKEND"));
        
        if (backend.isPresent() && backend.get().equals("mongo"))
            dao = daoFactory.createMongoUserDAO();
        else
            dao = daoFactory.createSQLUserDAO();

        var grpcServerName = Optional.ofNullable(System.getenv("GRPC_SERVER"));
        var grpcServerPort = Optional.ofNullable(System.getenv("GRPC_SERVER_PORT"));

        channel = ManagedChannelBuilder
                .forAddress(grpcServerName.orElse("localhost"), Integer.parseInt(grpcServerPort.orElse("50051")))
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS
                // to avoid needing certificates.
                .usePlaintext().build();
        blockingStub = GrpcServiceGrpc.newBlockingStub(channel);
        //asyncStub = GrpcServiceGrpc.newStub(channel);
    }

    public static AppLogicImpl getInstance()
    {
        return instance;
    }

    public Optional<User> getUserByEmail(String userId)
    {
        Optional<User> u = dao.getUserByEmail(userId);
        return u;
    }

    public Optional<User> getUserById(String userId)
    {
        return dao.getUserById(userId);
    }

    public boolean ping(int v)
    {
    	logger.info("Issuing ping, value: " + v);
    	
        // Test de grpc, puede hacerse con la BD
    	var msg = PingRequest.newBuilder().setV(v).build();
        var response = blockingStub.ping(msg);
        
        return response.getV() == v;
    }

    // El frontend, a través del formulario de login,
    // envía el usuario y pass, que se convierte a un DTO. De ahí
    // obtenemos la consulta a la base de datos, que nos retornará,
    // si procede,
    public Optional<User> checkLogin(String email, String pass)
    {
        Optional<User> u = dao.getUserByEmail(email);


        if (u.isPresent())
        {
            System.out.println("applogic: usuario recuperado" + u.get().toString());
            String hashed_pass = UserUtils.md5pass(pass);
            System.out.println("Contraseña recibida (hashed): " + hashed_pass + " \nContraseña almacenada: " + u.get().getPassword_hash());
            if (0 == hashed_pass.compareTo(u.get().getPassword_hash()))
                return u;
        }
        System.out.println("applogic: Nose ha encontrado el user para login");
        return Optional.empty();
    }

    public boolean registerUser(User user){
        return dao.registerUser(user);
    }

    //helper para el register
    public boolean userExists(User u){
        if(!dao.getUserByEmail(u.getEmail()).equals(Optional.empty())){
            System.out.println("applogic: El email ya existe");
            return true;
        }
        if (!dao.getUserById(u.getId()).equals(Optional.empty())){
            System.out.println("applogic: El id ya existe");
            return true;
        }
        System.out.println("applogic: el usuario no existe");
        return false;
        

        // if(!(dao.getUserByEmail(u.getEmail()).equals(Optional.empty())
        //         || dao.getUserById(u.getId()).equals(Optional.empty()))){ 
        //     return true;
        // }
        // return false;

        // return dao.getUserByEmail(u.getEmail()).equals(Optional.empty()) 
        //     ? dao.getUserById(u.getId()).equals(Optional.empty()) 
        //         ? true
        //         : false
        //     : false;
    }

    //////////////////////// CHATS /////////////////////
    public List<Chat> getChatList(){
        
    }
}
