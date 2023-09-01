/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaDeAccesoADatos;

import logicaDeNegocios.ICuenta;

/**
 *
 * @author sebashdez
 */
public interface IDAOCuentaIndividual {
    public abstract boolean registrarCuenta(String pNumeroCuenta, String pPin, 
            String pFechaCreacion, String pSaldo, String pEstatus);
    
    public abstract boolean depositar(String pNumeroCuenta, double pMontoDeposito); 
    public abstract boolean retirar(String pNumeroCuenta, double pMontoRetiro);
    public abstract boolean cambiarPin(String pNumeroCuenta, String pNuevoPin);
    public abstract boolean actualizarEstatus(String pNumeroCuenta, String pNuevoEstatus);
    public abstract ICuenta consultarCuenta(String pNumeroCuenta);            
    public abstract double consultarSaldoActual(String pNumeroCuenta);
    public abstract String consultarEstatusCuenta(String pNumeroCuenta);                    
    public abstract boolean verificarHayFondosSuficientes(String pNumeroCuenta, double pMontoOperacion);
    public abstract boolean verificarPinCorrespondeACuenta(String pNumeroCuenta, String pPin);
}
