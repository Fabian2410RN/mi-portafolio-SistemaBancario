/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeNegocios;

import java.time.LocalDate;
import listaDinamica.Lista;
import listaDinamica.Nodo;

/**
 *
 * @author Jairo Calderón
 */
public class RegistroDeBitacora {
    protected LocalDate fecha;
    protected String tipoDeAccion;
    protected String vista;
    private Lista<Bitacora> bitacorasAsociadas;

    public RegistroDeBitacora(LocalDate pFecha, String pTipoDeAccion, String pVista) {
        this.fecha = pFecha;
        this.tipoDeAccion = pTipoDeAccion;
        this.vista = pVista;
        bitacorasAsociadas = new Lista<>();
    }
    
    public void agregarBitacora(Bitacora pBitacora) {
        this.bitacorasAsociadas.agregarNodo(pBitacora);
    }
    
    public void registrarEnBitacoras() {
        Nodo puntero = this.bitacorasAsociadas.inicio;
        while(puntero != null) {
            Bitacora bitacora = (Bitacora) puntero.objeto;
            bitacora.agregarRegistro();
            puntero = puntero.siguiente;
        }
    }
}
