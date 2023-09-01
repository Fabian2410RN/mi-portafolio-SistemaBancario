/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonMensajesDeUsuario;

import mensajesDeUsuario.MensajeDeInformacionDeCuenta;


/**
 *
 * @author Jairo Calderón
 */
public class InformacionDeCuentaSingleton {
    private static MensajeDeInformacionDeCuenta instancia;
    
    private InformacionDeCuentaSingleton() {}
    
    public static MensajeDeInformacionDeCuenta instanciar() {
        if(instancia == null) {
            instancia = new MensajeDeInformacionDeCuenta();
        }
        return instancia;
    }
}
