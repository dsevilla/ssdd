/**
 *
 */
package es.um.sisdist.backend.dao.user;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static java.util.Arrays.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import es.um.sisdist.backend.dao.models.User;

/**
 * @author dsevilla
 *
 */
public class MongoUserDAO implements IUserDAO
{
    private MongoCollection<User> collection;
    private Logger logger;

    public MongoUserDAO()
    {
        logger = Logger.getLogger(MongoUserDAO.class.getName());
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().conventions(asList(Conventions.ANNOTATION_CONVENTION)).automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb://root:root@"
        		+ Optional.ofNullable(System.getenv("MONGO_SERVER")).orElse("localhost")
                + ":27017/ssdd?authSource=admin";

        // Create a MongoClient with the connection string
       	MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient
            .getDatabase(Optional.ofNullable(System.getenv("DB_NAME")).orElse("ssdd"))
        	.withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection("users", User.class);
    }

    @Override
    public Optional<User> getUserById(String id)
    {
        Optional<User> user = Optional.ofNullable(collection.find(eq("id", id)).first());
        return user;
    }

    @Override
    public Optional<User> getUserByEmail(String id)
    {
        Optional<User> user = Optional.ofNullable(collection.find(eq("email", id)).first());
        return user;
    }

    @Override
    public boolean registerUser(User newUser) {
        try {
            if (getUserByEmail(newUser.getEmail()).isPresent()) {
                logger.warning("Ya existe un usuario con el correo: " + newUser.getEmail());
                return false;
            }

            // String uuid = UUID.randomUUID().toString();
            // newUser.setId(uuid);
            // newUser.setToken(UserUtils.md5pass(newUser.getId() + newUser.getEmail()));  // Sigue incluyendo email si quieres

            InsertOneResult res = collection.insertOne(newUser);
            return res.wasAcknowledged();
        } catch (MongoException mongoEx) {
            logger.log(Level.SEVERE, "No se ha podido registrar el usuario en la Base de Datos.", mongoEx);
            return false;
        }
    }

    @Override
    public boolean deleteUserByID(String userID) {
        try {
            DeleteResult result = collection.deleteOne(eq("id", userID));
            return result.getDeletedCount() > 0;
        } catch (MongoException e) {
            logger.log(Level.SEVERE, "Error deleting user by ID", e);
            return false;
        }
    }

    @Override
    public boolean addNewChat(String userId, String chatId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewChat'");
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        try {
            DeleteResult result = collection.deleteOne(eq("email", email));
            return result.getDeletedCount() > 0;
        } catch (MongoException e) {
            logger.log(Level.SEVERE, "Error deleting user by email", e);
            return false;
        }
    }

    @Override
    public boolean updateUser(User updatedUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

}
