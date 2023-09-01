/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import mensajesDeUsuario.MensajeDeErrorDeCliente;
import mensajesDeUsuario.MensajeDeMovimientoDeClienteExitoso;
import singletonMensajesDeUsuario.ErrorDeClienteSingleton;
import singletonMensajesDeUsuario.MovimientoDeClienteExitosoSingleton;

/**
 *
 * @author Jairo Calderón
 */
public class MensajeEnPantallaCliente {
    private static MensajeDeMovimientoDeClienteExitoso mensajeDeExito = MovimientoDeClienteExitosoSingleton.instanciar();
    private static MensajeDeErrorDeCliente mensajeDeError = ErrorDeClienteSingleton.instanciar();
    
    public static void imprimirMensajeCreadoExitoso(String pCodigo, String pNombre, String pIdentificacion, String pFechaNacimiento, String pNumeroTelefono) {
        String mensaje = mensajeDeExito.imprimirMensajeClienteCreadoExitoso(pCodigo, pNombre, pIdentificacion, pFechaNacimiento, pNumeroTelefono);
        JOptionPane.showMessageDialog(null, mensaje, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirErrorIdentificacionInvalida() {
        String mensaje = mensajeDeError.imprimirErrorIdentificacionInvalida();
        JOptionPane.showMessageDialog(null, mensaje,"Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto() {
        String mensaje = mensajeDeError.imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto();
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirErrorFormatoDeCorreoIncorrecto() {
        String mensaje = mensajeDeError.imprimirErrorFormatoDeCorreoIncorrecto();
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
       
    public static void imprimirErrorIdentificacionExistente() {
        String mensaje = mensajeDeError.imprimirErrorIdentificacionInexistente();
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirErrorIdentificacionYaExistente() {
        String mensaje = mensajeDeError.imprimirErrorIdentificacionExistente();
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
