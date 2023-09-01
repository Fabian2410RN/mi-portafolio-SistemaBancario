/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonMensajesDeUsuario;

import mensajesDeUsuario.MensajeDeMovimientoDeClienteExitoso;

/**
 *
 * @author Jairo Calderón
 */
public class MovimientoDeClienteExitosoSingleton {
    private static MensajeDeMovimientoDeClienteExitoso instancia;
    
    private MovimientoDeClienteExitosoSingleton() {}
    
    public static MensajeDeMovimientoDeClienteExitoso instanciar() {
        if(instancia == null) {
            instancia = new MensajeDeMovimientoDeClienteExitoso();
        }
        return instancia;
    }
}
