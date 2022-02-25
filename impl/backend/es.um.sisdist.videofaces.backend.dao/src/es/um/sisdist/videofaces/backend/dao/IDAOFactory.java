/**
 *
 */
package es.um.sisdist.videofaces.backend.dao;

import es.um.sisdist.videofaces.backend.dao.user.IUserDAO;

/**
 * @author dsevilla
 *
 */
public interface IDAOFactory
{
    public IUserDAO createSQLUserDAO();
}
