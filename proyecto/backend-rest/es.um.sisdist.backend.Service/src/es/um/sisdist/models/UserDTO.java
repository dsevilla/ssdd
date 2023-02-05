package es.um.sisdist.models;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDTO
{
    private String id;
    private String email;
    private String password;
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
    public void setId(String id)
    {
        this.id = id;
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
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
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
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the tOKEN
     */
    public String getToken()
    {
        return token;
    }

    /**
     * @param tOKEN the tOKEN to set
     */
    public void setToken(String tOKEN)
    {
        token = tOKEN;
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
    public void setVisits(int visits)
    {
        this.visits = visits;
    }

    public UserDTO(String id, String email, String password, String name, String tOKEN, int visits)
    {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        token = tOKEN;
        this.visits = visits;
    }

    public UserDTO()
    {
    }
}
