/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaDeAccesoADatos;

import listaDinamica.Lista;
import logicaDeNegocios.Operacion;

/**
 *
 * @author sebashdez
 */
public interface IDAOOperacionCuenta {
    public abstract boolean registrarOperacion(String pNumeroCuentaAsocidada, String pFechaOperacion, String pTipoOperacion, boolean pSeAplicoComision, double pMontoComision);
    public abstract Lista<Operacion> consultarOperacionesCuenta(String pNumeroCuenta);
    public abstract int consultarCantidadDeDepositosYRetirosRealizados(String pNumeroCuenta);
    public abstract double consultarMontoTotalCobradoComisionesPorDepositos(String pNumeroCuenta);
    public abstract double consultarMontoTotalCobradoComisionesPorRetiros(String pNumeroCuenta);
    public abstract double consultarMontoTotalCobradoComisionesPorRetirosYDepositos(String pNumeroCuenta);
    public abstract int consultarCantidadOperaciones(String pNumeroCuenta);
}
