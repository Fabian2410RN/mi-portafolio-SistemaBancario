/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajesDeUsuario;

/**
 *
 * @author Jairo Calderón
 */
public class MensajeDeMovimientoDeClienteExitoso {
    public String imprimirMensajeClienteCreadoExitoso(String pCodigo, String pNombre, String pIdentificacion, String pFechaNacimiento, String pNumeroTelefono) {
        String mensaje = "Se ha creado un nuevo cliente en el sistema, los datos que fueron almacenados son: \n";
        mensaje += "Código: " + pCodigo + "\n";
        mensaje += "Nombre: " + pNombre + "\n";
        mensaje += "Identificación: " + pIdentificacion + "\n";
        mensaje += "Fecha de nacimiento: " + pFechaNacimiento+ "\n";
        mensaje += "Número de teléfono: " + pNumeroTelefono;
        return mensaje;
    }
}
