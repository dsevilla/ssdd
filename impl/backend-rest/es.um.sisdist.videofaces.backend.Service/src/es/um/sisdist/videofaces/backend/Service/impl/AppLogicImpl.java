/**
 * 
 */
package es.um.sisdist.videofaces.backend.Service.impl;

import java.util.Optional;

import es.um.sisdist.videofaces.backend.dao.IDAOFactory;
import es.um.sisdist.videofaces.backend.dao.user.DAOFactory;
import es.um.sisdist.videofaces.backend.dao.user.IUserDAO;
import es.um.sisdist.videofaces.backend.dao.user.models.User;

/**
 * @author dsevilla
 *
 */
public class AppLogicImpl
{
    IDAOFactory daoFactory;
    IUserDAO dao;
    
	static AppLogicImpl instance = new AppLogicImpl();
	
	private AppLogicImpl()
	{
		daoFactory = new DAOFactory();
		dao = daoFactory.createSQLUserDAO();
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

	public User getUserById(String userId)
	{
		return new User(userId, userId, userId, userId,1);
	}

}
