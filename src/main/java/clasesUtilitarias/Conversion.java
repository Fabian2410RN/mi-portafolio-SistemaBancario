/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesUtilitarias;

import java.time.LocalDate;
import listaDinamica.Lista;
import listaDinamica.Nodo;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.Operacion;

/**
 *
 * @author Jairo Calderon
 */
public class Conversion {
    public int convertirStringEnEntero(String pCadena) {
        return Integer.parseInt(pCadena);
    }
    
    public double convertirStringEnDecimal(String pCadena) {
        return Double.parseDouble(pCadena);
    }
    
    public LocalDate convertirStringEnLocalDate(String pCadena) {
        String[] partesDeString = pCadena.split("/");
        int dia = convertirStringEnEntero(partesDeString[0]);
        int mes = convertirStringEnEntero(partesDeString[1]);
        int ano = convertirStringEnEntero(partesDeString[2]);
        return LocalDate.of(ano, mes, dia);
    }
    
    public Cliente[] convertirListaClienteEnArreglo(Lista<ICliente> pLista, int tamano) {
        Cliente[] arregloCliente = new Cliente[tamano];
        Nodo puntero = pLista.inicio;
        int indiceArreglo = 0;
        while(puntero != null) {
            Cliente cliente = (Cliente) puntero.objeto;
            arregloCliente[indiceArreglo] = cliente;
            puntero = puntero.siguiente;
            indiceArreglo++;
        }
        return arregloCliente;
    }
    
    public Cuenta[] convertirListaCuentaEnArreglo(Lista<ICuenta> pLista, int tamano) {
        Cuenta[] arregloCuenta = new Cuenta[tamano];
        Nodo puntero = pLista.inicio;
        int indiceArreglo = 0;
        while(puntero != null) {
            Cuenta cuenta = (Cuenta) puntero.objeto;
            arregloCuenta[indiceArreglo] = cuenta;
            puntero = puntero.siguiente;
            indiceArreglo++;
        }
        return arregloCuenta;
    }
    
    public Operacion[] convertirListaOperacionEnArreglo(Lista<Operacion> pLista, int tamano) {
        Operacion[] arregloOperacion = new Operacion[tamano];
        Nodo puntero = pLista.inicio;
        int indiceArreglo = 0;
        while(puntero != null) {
            Operacion operacion = (Operacion) puntero.objeto;
            arregloOperacion[indiceArreglo] = operacion;
            puntero = puntero.siguiente;
            indiceArreglo++;
        }
        return arregloOperacion;
    }
}
