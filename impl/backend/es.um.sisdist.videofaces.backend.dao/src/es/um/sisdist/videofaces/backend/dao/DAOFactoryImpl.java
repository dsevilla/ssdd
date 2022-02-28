/**
 * 
 */
package es.um.sisdist.videofaces.backend.dao;

import es.um.sisdist.videofaces.backend.dao.user.IUserDAO;
import es.um.sisdist.videofaces.backend.dao.user.SQLUserDAO;

/**
 * @author dsevilla
 *
 */
public class DAOFactoryImpl implements IDAOFactory
{
	@Override
	public IUserDAO createSQLUserDAO()
	{
		return new SQLUserDAO();
	}

}
