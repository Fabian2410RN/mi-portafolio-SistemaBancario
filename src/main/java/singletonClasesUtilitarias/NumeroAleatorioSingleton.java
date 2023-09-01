/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonClasesUtilitarias;

import clasesUtilitarias.NumeroAleatorio;

/**
 *
 * @author Jairo Calderón
 */
public class NumeroAleatorioSingleton {
    private static NumeroAleatorio instancia;
    
    private NumeroAleatorioSingleton() {}
    
    public static NumeroAleatorio instanciar() {
        if(instancia == null) {
            instancia = new NumeroAleatorio();
        }
        return instancia;
    }
}
