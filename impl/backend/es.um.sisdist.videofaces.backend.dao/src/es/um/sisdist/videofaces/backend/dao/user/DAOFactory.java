/**
 * 
 */
package es.um.sisdist.videofaces.backend.dao.user;

import es.um.sisdist.videofaces.backend.dao.IDAOFactory;

/**
 * @author dsevilla
 *
 */
public class DAOFactory implements IDAOFactory
{
	@Override
	public IUserDAO createSQLUserDAO()
	{
		return new SQLUserDAO();
	}

}
