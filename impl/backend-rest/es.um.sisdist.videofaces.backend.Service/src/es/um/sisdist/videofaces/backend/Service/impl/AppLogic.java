/**
 * 
 */
package es.um.sisdist.videofaces.backend;

import es.um.sisdist.videofaces.models.User;

/**
 * @author dsevilla
 *
 */
public class AppLogic
{
	static AppLogic instance = new AppLogic();
	
	private AppLogic()
	{
	}
	
	public static AppLogic getInstance()
	{
		return instance;
	}
	
	public User getUser(String userId)
	{
		return new User(userId, userId, userId);
	}
}
