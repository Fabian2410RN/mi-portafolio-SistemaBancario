/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaDeNegocios;

/**
 *
 * @author Jairo Calderón
 */
public interface CorreoDeCuenta {
    public String generarMensajeDeCorreoInactivacionDeCuenta(String pMotivoInactivacion);
    public String generarAsuntoDeCorreoInactivacionDeCuenta();
}
