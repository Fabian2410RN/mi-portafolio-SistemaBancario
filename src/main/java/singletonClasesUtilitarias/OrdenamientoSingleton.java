/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonClasesUtilitarias;

import clasesUtilitarias.Ordenamiento;

/**
 *
 * @author Jairo Calderón
 */
public class OrdenamientoSingleton {
    private static Ordenamiento instancia;
    
    private OrdenamientoSingleton() {}
    
    public static Ordenamiento instanciar() {
        if(instancia == null) {
            instancia = new Ordenamiento();
        }
        return instancia;
    }
}
