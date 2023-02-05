/**
 *
 */
package es.um.sisdist.backend.dao;

import es.um.sisdist.backend.dao.user.IUserDAO;
import es.um.sisdist.backend.dao.user.MongoUserDAO;
import es.um.sisdist.backend.dao.user.SQLUserDAO;

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

    @Override
    public IUserDAO createMongoUserDAO()
    {
        return new MongoUserDAO();
    }
}
