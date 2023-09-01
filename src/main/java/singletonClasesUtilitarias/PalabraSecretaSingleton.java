/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonClasesUtilitarias;

import clasesUtilitarias.PalabraSecreta;

/**
 *
 * @author Jairo Calderón
 */
public class PalabraSecretaSingleton {
    private static PalabraSecreta instancia;
    
    private PalabraSecretaSingleton() {}
    
    public static PalabraSecreta instanciar() {
        if(instancia == null) {
            instancia = new PalabraSecreta();
        }
        return instancia;
    }
}
