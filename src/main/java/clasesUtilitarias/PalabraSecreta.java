/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesUtilitarias;

import singletonClasesUtilitarias.NumeroAleatorioSingleton;

/**
 *
 * @author Jairo Calderon
 */
public class PalabraSecreta {
    
    public String generarPalabraSecreta() {
        String palabraSecreta = "";
        for(int i = 0; i < 5; i++) {
            palabraSecreta += generarCaracterAleatorio();
        }
        return palabraSecreta;
    }
    
    private char generarCaracterAleatorio() {
        NumeroAleatorio generadorDeNumeroAletorio = NumeroAleatorioSingleton.instanciar();
        int letraONumero = generadorDeNumeroAletorio.generarNumeroAleatorioEnRango(1, 2);
        if(letraONumero == 1) {
            int mayusculaOMinuscula = generadorDeNumeroAletorio.generarNumeroAleatorioEnRango(1, 2);
            if(mayusculaOMinuscula == 1) {
                    int valorASCII = generadorDeNumeroAletorio.generarNumeroAleatorioEnRango(65, 90);
                    char mayuscula = (char) valorASCII;
                    return mayuscula;
            }
            else {
                    int valorASCII = generadorDeNumeroAletorio.generarNumeroAleatorioEnRango(97, 122);
                    char minuscula = (char) valorASCII;
                    return minuscula;
            }
        }
        else {
            int valorASCII = generadorDeNumeroAletorio.generarNumeroAleatorioEnRango(48, 57);
            char numeroCaracter = (char) valorASCII;
            return numeroCaracter;
        }
    }
}
