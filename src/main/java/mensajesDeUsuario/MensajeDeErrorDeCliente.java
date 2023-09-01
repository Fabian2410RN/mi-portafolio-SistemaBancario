/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajesDeUsuario;

/**
 *
 * @author Jairo Calderón
 */
public class MensajeDeErrorDeCliente {
    public String imprimirErrorIdentificacionInvalida() {
        String mensaje = "La identificación debe ser un número entero";
        return mensaje;
    }
    
    public String imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto() {
        String mensaje = "Asegúrese de que su número de telefono incluya ocho dígitos";
        return mensaje;
    }
    
    public String imprimirErrorFormatoDeCorreoIncorrecto() {
        String mensaje = "Asegúrese de que su correo tenga un formato correcto";
        return mensaje;
    }
       
    public String imprimirErrorIdentificacionExistente() {
        String mensaje = "Ya existe un cliente con esa identificación";
        return mensaje;
    }
    
    public String imprimirErrorIdentificacionInexistente() {
        String mensaje = "No existe ningún cliente con esa identificación";
        return mensaje;
    }
    
    public String imprimirErrorFechaDeNacimientoIncorrecta() {
        String mensaje = "El formato de la fecha de nacimiento no es correcto";
        return mensaje;
    }
}
