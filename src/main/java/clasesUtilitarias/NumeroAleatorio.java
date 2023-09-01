/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesUtilitarias;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jairo Calderón
 */
public class NumeroAleatorio {
    public int generarNumeroAleatorioEnRango(int pValorMinimo, int pValorMaximo) {
        ThreadLocalRandom aleatorio = ThreadLocalRandom.current();
        int numeroAleatorio = aleatorio.nextInt(pValorMinimo, pValorMaximo + 1);
        return numeroAleatorio;
    }
}
