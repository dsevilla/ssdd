package es.um.sisdist.videofaces.backend.dao.user;

import es.um.sisdist.videofaces.backend.dao.user.model.User;

public interface IUserDAO
{
    public User getUser(String id);
}
