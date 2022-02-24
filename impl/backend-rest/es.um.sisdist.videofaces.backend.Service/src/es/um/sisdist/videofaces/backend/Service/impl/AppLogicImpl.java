/**
 * 
 */
package es.um.sisdist.videofaces.backend.Service.impl;

import es.um.sisdist.videofaces.backend.dao.IDAOFactory;
import es.um.sisdist.videofaces.models.User;

/**
 * @author dsevilla
 *
 */
public class AppLogicImpl
{
    static IDAOFactory daoFactory;
    
	static AppLogicImpl instance = new AppLogicImpl();
	
	private AppLogicImpl()
	{
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
