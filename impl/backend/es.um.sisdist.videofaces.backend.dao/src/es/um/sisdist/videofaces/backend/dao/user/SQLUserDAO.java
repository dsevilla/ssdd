/**
 * 
 */
package es.um.sisdist.videofaces.backend.dao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import es.um.sisdist.videofaces.backend.dao.user.model.User;

/**
 * @author dsevilla
 *
 */
@SuppressWarnings("deprecation")
public class SQLUserDAO implements IUserDAO
{
	Connection conn;
	
	public SQLUserDAO()
	{
        try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/videofaces?" +
					"user=root&password=root");
//			Statement stm = conn.createStatement();
//			int result = 
//					stm.executeUpdate("INSERT INTO users VALUES (1, 'd@d', 'abc', 'aaaa')");
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Optional<User> getUser(String id)
	{
		return Optional.empty();
	}

}
