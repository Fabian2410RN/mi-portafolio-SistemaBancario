/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaDeAccesoADatos;

import listaDinamica.Lista;
import logicaDeNegocios.ICuenta;

/**
 *
 * @author sebashdez
 */
public interface IDAOCatalogoDeCuentas {
    public abstract Lista<ICuenta> consultarListaDeCuentas(); 
    public abstract int consultarCantidadCuentas();
    public abstract boolean consultarSiExisteCuenta(String pNumeroCuenta);
}
