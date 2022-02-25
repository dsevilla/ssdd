package es.um.sisdist.videofaces.backend.dao.user;

import java.util.Optional;

import es.um.sisdist.videofaces.backend.dao.user.model.User;

public interface IUserDAO
{
    public Optional<User> getUser(String id);
}
