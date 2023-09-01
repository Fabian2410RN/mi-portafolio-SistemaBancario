/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaDeAccesoADatos;

import listaDinamica.Lista;
import logicaDeNegocios.ICliente;

/**
 *
 * @author sebashdez
 */
public interface IDAOCatalogoDeClientes {
    public abstract Lista<ICliente> consultarListaDeClientes();
    public abstract int consultarCantidadDeClientes();
    public abstract boolean consultarSiExisteCliente(int pIdentificacion);
    public abstract boolean consultarSiNOExisteCliente(int pIdentificacion);
}
