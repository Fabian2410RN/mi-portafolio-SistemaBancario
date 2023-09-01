/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaDeAccesoADatos;

import logicaDeNegocios.ICliente;

/**
 *
 * @author sebashdez
 */
public interface IDAOCliente{
    public abstract boolean registrarCliente(String pCodigo, String pNombre, String pPrimerApellido, String pSegundoApellido, int pIdentificacion, String pFechaNacimiento, int pNumeroTelefono,  String pCorreoElectronico);
    public abstract ICliente consultarCliente(int pIdentificacion);
}
