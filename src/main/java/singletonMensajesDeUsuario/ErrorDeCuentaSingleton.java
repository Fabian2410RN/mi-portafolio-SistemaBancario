/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonMensajesDeUsuario;

import mensajesDeUsuario.MensajeDeErrorDeCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ErrorDeCuentaSingleton {
    private static MensajeDeErrorDeCuenta instancia;
    
    private ErrorDeCuentaSingleton() {}
    
    public static MensajeDeErrorDeCuenta instanciar() {
        if(instancia == null) {
            instancia = new MensajeDeErrorDeCuenta();
        }
        return instancia;
    }
}
