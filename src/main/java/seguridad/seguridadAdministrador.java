package seguridad;

/**
 *
 * @author estadm
 */
public class seguridadAdministrador {
    
    public static boolean iniciarSesionAdministrador(String pUsuario, String pPin){
        return "JSF".equals(pUsuario) && "123".equals(pPin);
    }
}
