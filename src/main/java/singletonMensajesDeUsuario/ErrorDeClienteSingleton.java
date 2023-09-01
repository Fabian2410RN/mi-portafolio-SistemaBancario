/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonMensajesDeUsuario;

import mensajesDeUsuario.MensajeDeErrorDeCliente;

/**
 *
 * @author Jairo Calderón
 */
public class ErrorDeClienteSingleton {
    private static MensajeDeErrorDeCliente instancia;
    
    private ErrorDeClienteSingleton() {}
    
    public static MensajeDeErrorDeCliente instanciar() {
        if(instancia == null) {
            instancia = new MensajeDeErrorDeCliente();
        }
        return instancia;
    }
}
