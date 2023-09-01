/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.text.DecimalFormat;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOOperacionCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOOperacionCatalogoDeCuentas;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import vistaGUI.MenuPrincipal;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeGananciasTotalesDeBancoPorComisionesCLI {
    public ConsultaDeGananciasTotalesDeBancoPorComisionesCLI() {
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisiones();
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisiones() {
        System.out.println("Ganancias totales del Banco por el cobro de comisiones ");
        System.out.println("\nResultados en colones: ");
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones();
        System.out.println("\nResultados en dólares: ");
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares();
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Ganancia por comisión del banco", "CLI");
        MenuPrincipal volverAMenuPrincipal = new MenuPrincipal();
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones() {
        IDAOOperacionCatalogoDeCuentas comisionesCobradas = new DAOOperacionCatalogoDeCuentas();
        double comisionesTotalesDepositos = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos();
        double comisionesTotalesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros();
        double comisionesTotalesDepositoRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos();
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        System.out.println("Ganancias por depositos: " + formatoDeNumeroDecimal.format(comisionesTotalesDepositos));
        System.out.println("Ganancias por retiros: " + formatoDeNumeroDecimal.format(comisionesTotalesRetiros));
        System.out.println("Ganancias por depositos y retiros: " + formatoDeNumeroDecimal.format(comisionesTotalesDepositoRetiros));
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares() {
        IDAOOperacionCatalogoDeCuentas comisionesCobradas = new DAOOperacionCatalogoDeCuentas();
        double comisionesTotalesDepositos = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos();
        double comisionesTotalesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros();
        double comisionesTotalesDepositoRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos();
        TipoCambioBCCR tipoDeCambio = new TipoCambioBCCR();
        double valorDeCompraDelDolar = tipoDeCambio.obtenerValorCompra();
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        System.out.println("Ganancias por depositos: " + formatoDeNumeroDecimal.format((comisionesTotalesDepositos * valorDeCompraDelDolar)));
        System.out.println("Ganancias por retiros: " + formatoDeNumeroDecimal.format((comisionesTotalesRetiros * valorDeCompraDelDolar)));
        System.out.println("Ganancias por depositos y retiros: " + formatoDeNumeroDecimal.format((comisionesTotalesDepositoRetiros * valorDeCompraDelDolar)));
    }
}
