/**
 *
 */
package es.um.sisdist.models;

import es.um.sisdist.backend.dao.models.User;
import es.um.sisdist.backend.dao.models.utils.UserUtils;

/**
 * @author dsevilla
 *
 */
public class UserDTOUtils
{
    public static User fromDTO(UserDTO udto)
    {

        System.out.println("Convirtiendo userDTO a User");
        System.out.println("contraseña antigua: " + udto.getPassword());
        udto.setPassword(UserUtils.md5pass(udto.getPassword())); // TODO borrar sysouts de debug
        System.out.println("Contraseña que se almacenará: " + udto.getPassword());
        return new User(udto.getId(), udto.getEmail(), udto.getPassword(), 
            udto.getName(), udto.getToken(), udto.getVisits());
    }

    public static UserDTO toDTO(User u)
    {
        return new UserDTO(u.getId(), u.getEmail(), "", // Password never is returned back
                u.getName(), u.getToken(), u.getVisits());
    }
}
