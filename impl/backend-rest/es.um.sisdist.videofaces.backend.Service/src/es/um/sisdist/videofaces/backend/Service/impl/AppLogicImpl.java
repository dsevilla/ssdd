/**
 * 
 */
package es.um.sisdist.videofaces.backend.Service.impl;

import java.util.Optional;

import es.um.sisdist.videofaces.backend.dao.DAOFactoryImpl;
import es.um.sisdist.videofaces.backend.dao.IDAOFactory;
import es.um.sisdist.videofaces.backend.dao.models.User;
import es.um.sisdist.videofaces.backend.dao.user.IUserDAO;
import es.um.sisdist.videofaces.models.UserDTO;
import es.um.sisdist.videofaces.models.UserDTOUtils;

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
		daoFactory = new DAOFactoryImpl();
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

	public Optional<User> getUserById(String userId)
	{
		return dao.getUserById(userId);
	}

	// El frontend, a través del formulario de login, 
	// envía el usuario y pass, que se convierte a un DTO. De ahí
	// obtenemos la consulta a la base de datos, que nos retornará,
	// si procede, 
	public Optional<UserDTO> checkLogin(UserDTO userToCheck)
	{
		Optional<User> u = dao.getUserByEmail(userToCheck.getEmail());
		
		if (u.isPresent())
		{
			if (User.md5pass(userToCheck.getPassword()) == u.get().getPassword_hash())
				return Optional.of(UserDTOUtils.toDTO(u.get()));
		}
		
		return Optional.empty();
	}
}
