package es.um.sisdist.backend.dao.user;

import java.util.Optional;

import es.um.sisdist.backend.dao.models.User;

public interface IUserDAO
{
    public Optional<User> getUserById(String id);

    public Optional<User> getUserByEmail(String id);

    public boolean registerUser(User newUser);

    public boolean deleteUserByID(String userID);

    public boolean addNewChat(String userId, String chatId);
    
    public boolean deleteUserByEmail(String email);

    public boolean updateUser(User updatedUser);
}
