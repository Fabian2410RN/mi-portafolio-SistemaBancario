/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonClasesUtilitarias;

import clasesUtilitarias.ContenidoHTML;

/**
 *
 * @author Jairo Calderón
 */
public class ContenidoHTMLSingleton {
    private static ContenidoHTML instancia;
    
    private ContenidoHTMLSingleton() {}
    
    public static ContenidoHTML instanciar() {
        if(instancia == null) {
            instancia = new ContenidoHTML();
        }
        return instancia;
    }
}
