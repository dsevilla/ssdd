/**
 *
 */
package es.um.sisdist.backend.dao.models;

import es.um.sisdist.backend.dao.models.utils.UserUtils;

public class User
{
    private String id;
    private String email;
    private String password_hash;
    private String name;

    private String token;

    private int visits;

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final String uid)
    {
        this.id = uid;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email)
    {
        this.email = email;
    }

    /**
     * @return the password_hash
     */
    public String getPassword_hash()
    {
        return password_hash;
    }

    /**
     * @param password_hash the password_hash to set
     */
    public void setPassword_hash(final String password_hash)
    {
        this.password_hash = password_hash;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @return the TOKEN
     */
    public String getToken()
    {
        return token;
    }

    /**
     * @param tOKEN the tOKEN to set
     */
    public void setToken(final String TOKEN)
    {
        this.token = TOKEN;
    }

    /**
     * @return the visits
     */
    public int getVisits()
    {
        return visits;
    }

    /**
     * @param visits the visits to set
     */
    public void setVisits(final int visits)
    {
        this.visits = visits;
    }

    public User(String email, String password_hash, String name, String tOKEN, int visits)
    {
        this(email, email, password_hash, name, tOKEN, visits);
        this.id = UserUtils.md5pass(email);
    }

    public User(String id, String email, String password_hash, String name, String tOKEN, int visits)
    {
        this.id = id;
        this.email = email;
        this.password_hash = password_hash;
        this.name = name;
        token = tOKEN;
        this.visits = visits;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", email=" + email + ", password_hash=" + password_hash + ", name=" + name
                + ", TOKEN=" + token + ", visits=" + visits + "]";
    }

    public User()
    {
    }
}