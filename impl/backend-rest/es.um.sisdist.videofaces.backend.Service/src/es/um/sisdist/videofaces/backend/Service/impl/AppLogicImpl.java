/**
 * 
 */
package es.um.sisdist.videofaces.backend.Service.impl;

import es.um.sisdist.videofaces.backend.dao.IDAOFactory;
import es.um.sisdist.videofaces.backend.dao.user.DAOFactory;
import es.um.sisdist.videofaces.backend.dao.user.IUserDAO;
import es.um.sisdist.videofaces.models.User;

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
	
	public User getUser(String userId)
	{
		return new User(userId, userId, userId);
	}
}
