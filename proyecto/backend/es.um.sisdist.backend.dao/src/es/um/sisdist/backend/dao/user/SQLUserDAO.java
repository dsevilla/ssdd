/**
 *
 */
package es.um.sisdist.backend.dao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import es.um.sisdist.backend.dao.models.User;

/**
 * @author dsevilla
 *
 */
public class SQLUserDAO implements IUserDAO
{
    Optional<Connection> conn;

    public SQLUserDAO()
    {
        // Generate an optional from a direct connection attempt

        Connection connection = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();

            // Si el nombre del host se pasa por environment, se usa aquí.
            // Si no, se usa localhost. Esto permite configurarlo de forma
            // sencilla para cuando se ejecute en el contenedor, y a la vez
            // se pueden hacer pruebas locales
            String sqlServerName = Optional.ofNullable(System.getenv("SQL_SERVER")).orElse("localhost");
            String dbName = Optional.ofNullable(System.getenv("DB_NAME")).orElse("ssdd");
            connection = DriverManager.getConnection(
                "jdbc:mysql://" + sqlServerName + "/" + dbName + "?user=root&password=root");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        conn = Optional.ofNullable(connection);
    }


    @Override
    public Optional<User> getUserById(String id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> getUserByEmail(String id)
    {
        Optional<PreparedStatement> stm;
        if (conn.isEmpty())
            return Optional.empty();

        try
        {
            stm = Optional.ofNullable(conn.get().prepareStatement("SELECT * from users WHERE email = ?"));
            if (stm.isEmpty())
                return Optional.empty();
            stm.get().setString(1, id);
            ResultSet result = stm.get().executeQuery();
            if (result.next())
                return createUser(result);
        } catch (SQLException e)
        {
            // Fallthrough
        }
        return Optional.empty();
    }

    private Optional<User> createUser(ResultSet result)
    {
        try
        {
            return Optional.of(new User(result.getString(1), // id
                    result.getString(2), // email
                    result.getString(3), // pwhash
                    result.getString(4), // name
                    result.getString(5), // token
                    result.getInt(6))); // visits
        } catch (SQLException e)
        {
            return Optional.empty();
        }
    }
}
