/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonClasesUtilitarias;

import clasesUtilitarias.Conversion;

/**
 *
 * @author Jairo Calderón
 */
public class ConversionSingleton {
    private static Conversion instancia;
    
    private ConversionSingleton() {}
    
    public static Conversion instanciar() {
        if(instancia == null) {
            instancia = new Conversion();
        }
        return instancia;
    }
}
