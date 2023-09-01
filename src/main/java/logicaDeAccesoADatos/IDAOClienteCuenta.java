/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaDeAccesoADatos;

import listaDinamica.Lista;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.ICuenta;

/**
 *
 * @author sebashdez
 */
public interface IDAOClienteCuenta {
    public abstract boolean registrarCuentaACliente(String pNumeroCuentaAsocidada, int pIdentificacion);
    public abstract Lista<ICuenta> consultarCuentasDeCliente(int pIdentificacion);
    public abstract ICliente consultarClienteAsociadoACuenta(String pNumeroCuenta);
    public abstract int consultarCantidadDeCuentasDeCliente(int pIdentificacionCliente);
}
