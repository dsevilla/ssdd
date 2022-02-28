/**
 * 
 */
package es.um.sisdist.videofaces.models;

import es.um.sisdist.videofaces.backend.dao.models.User;

/**
 * @author dsevilla
 *
 */
public class UserDTOUtils
{
	public static User fromDTO(UserDTO udto)
	{
		return new User(udto.getId(),
				udto.getEmail(),
				udto.getPassword(),
				udto.getName(),
				udto.getTOKEN(),
				udto.getVisits()
				);
	}
	
	public static UserDTO toDTO(User u)
	{
		return new UserDTO(u.getId(),
				u.getEmail(),
				"", // Password never is returned back
				u.getName(),
				u.getTOKEN(),
				u.getVisits()
				);
	}
}
