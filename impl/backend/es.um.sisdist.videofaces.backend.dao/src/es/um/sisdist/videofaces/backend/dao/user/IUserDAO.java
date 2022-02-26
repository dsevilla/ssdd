package es.um.sisdist.videofaces.backend.dao.user;

import java.util.Optional;

import es.um.sisdist.videofaces.backend.dao.models.User;

public interface IUserDAO
{
    public Optional<User> getUserById(String id);

    public Optional<User> getUserByEmail(String id);
}
