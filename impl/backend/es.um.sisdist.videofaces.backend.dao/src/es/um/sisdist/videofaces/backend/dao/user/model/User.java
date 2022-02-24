/**
 *
 */
package es.um.sisdist.videofaces.backend.dao.user.model;

/**
 * @author dsevilla
 *
 */
public class User
{
    private String id;
    private String email;
    private String hashPassword;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getHashPassword()
    {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword)
    {
        this.hashPassword = hashPassword;
    }

    public User(String id, String email, String hashPassword)
    {
        this.id = id;
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public User()
    {
    }
}
