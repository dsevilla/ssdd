/**
 *
 */
package es.um.sisdist.models;

import es.um.sisdist.backend.dao.models.User;

/**
 * @author dsevilla
 *
 */
public class UserDTOUtils
{
    public static User fromDTO(UserDTO udto)
    {
        return new User(udto.getId(), udto.getEmail(), udto.getPassword(), udto.getName(), udto.getToken(),
                udto.getVisits());
    }

    public static UserDTO toDTO(User u)
    {
        return new UserDTO(u.getId(), u.getEmail(), "", // Password never is returned back
                u.getName(), u.getToken(), u.getVisits());
    }
}
