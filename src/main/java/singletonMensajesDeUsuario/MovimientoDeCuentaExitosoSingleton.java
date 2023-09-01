/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonMensajesDeUsuario;

import mensajesDeUsuario.MensajeDeMovimientoDeCuentaExitoso;

/**
 *
 * @author Jairo Calderón
 */
public class MovimientoDeCuentaExitosoSingleton {
    private static MensajeDeMovimientoDeCuentaExitoso instancia;
    
    private MovimientoDeCuentaExitosoSingleton() {}
    
    public static MensajeDeMovimientoDeCuentaExitoso instanciar() {
        if(instancia == null) {
            instancia = new MensajeDeMovimientoDeCuentaExitoso();
        }
        return instancia;
    }
}
