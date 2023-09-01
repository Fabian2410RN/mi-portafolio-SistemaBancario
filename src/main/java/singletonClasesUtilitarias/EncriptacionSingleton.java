/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonClasesUtilitarias;

import clasesUtilitarias.Encriptacion;

/**
 *
 * @author Jairo Calderón
 */
public class EncriptacionSingleton {
    private static Encriptacion instancia;
    
    private EncriptacionSingleton() {}
    
    public static Encriptacion instanciar() {
        if(instancia == null) {
            instancia = new Encriptacion();
        }
        return instancia;
    }
}
