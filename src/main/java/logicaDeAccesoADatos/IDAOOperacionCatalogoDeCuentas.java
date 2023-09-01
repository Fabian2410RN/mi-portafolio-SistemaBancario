/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaDeAccesoADatos;

/**
 *
 * @author Jairo Calderón
 */
public interface IDAOOperacionCatalogoDeCuentas {
    public abstract double consultarMontoTotalCobradoComisionesPorDepositos();
    public abstract double consultarMontoTotalCobradoComisionesPorRetiros();
    public abstract double consultarMontoTotalCobradoComisionesPorRetirosYDepositos();
}
