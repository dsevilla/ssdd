package es.um.sisdist.videofaces.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User
{
	private String loginEmail;
	private String password;

	private String TOKEN;

	/**
	 * @return the loginEmail
	 */
	public String getLoginEmail()
	{
		return loginEmail;
	}

	/**
	 * @param loginEmail the loginEmail to set
	 */
	public void setLoginEmail(String loginEmail)
	{
		this.loginEmail = loginEmail;
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
	 * @return the tOKEN
	 */
	public String getTOKEN()
	{
		return TOKEN;
	}

	/**
	 * @param tOKEN the tOKEN to set
	 */
	public void setTOKEN(String tOKEN)
	{
		TOKEN = tOKEN;
	}

	public User(String loginEmail, String password, String tOKEN)
	{
		this.loginEmail = loginEmail;
		this.password = password;
		TOKEN = tOKEN;
	}
	
	public User()
	{
	}
}
