/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.text.DecimalFormat;
import java.time.LocalDate;
import listaDinamica.Lista;
import listaDinamica.Nodo;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import vistaGUI.MenuPrincipal;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeGananciasParaCadaCuentaPorCobroDeComisiones {
    public ConsultaDeGananciasParaCadaCuentaPorCobroDeComisiones() {
        mostrarGananciasTotalesDeBancoPorCobroDeComisiones();
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisiones() {
        IDAOCatalogoDeCuentas daoCuentas = new DAOCatalogoDeCuentas();
        Lista<ICuenta> listaDeCuentas = daoCuentas.consultarListaDeCuentas();
        Nodo puntero = listaDeCuentas.inicio;
        while(puntero != null) {
            Cuenta cuenta = (Cuenta) puntero.objeto;
            String numeroDeCuenta = cuenta.numeroCuenta;
            System.out.println("\nGanancias del Banco por el cobro de comisiones a la cuenta: " + numeroDeCuenta);
            System.out.println("\nResultados en colones: ");
            this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones(numeroDeCuenta);
            System.out.println("\nResultados en dólares: ");
            this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares(numeroDeCuenta);
            puntero = puntero.siguiente;
        }
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Ganancia por comisión cada cuenta", "CLI");
        MenuPrincipal volverAMenuPrincipal = new MenuPrincipal();
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones(String pNumeroDeCuenta) {
        IDAOOperacionCuenta comisionesCobradas = new DAOOperacionCuenta();
        double comisionesDepositos = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos(pNumeroDeCuenta);
        double comisionesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros(pNumeroDeCuenta);
        double comisionesDepositosYRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(pNumeroDeCuenta);
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        System.out.println("Ganancias por depositos: " + formatoDeNumeroDecimal.format(comisionesDepositos));
        System.out.println("Ganancias por retiros: " + formatoDeNumeroDecimal.format(comisionesRetiros));
        System.out.println("Ganancias por depositos y retiros: " + formatoDeNumeroDecimal.format(comisionesDepositosYRetiros));
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares(String pNumeroDeCuenta) {
        IDAOOperacionCuenta comisionesCobradas = new DAOOperacionCuenta();
        double comisionesDepositos = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos(pNumeroDeCuenta);
        double comisionesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros(pNumeroDeCuenta);
        double comisionesDepositosYRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(pNumeroDeCuenta);
        TipoCambioBCCR tipoDeCambio = new TipoCambioBCCR();
        double valorDeCompraDelDolar = tipoDeCambio.obtenerValorCompra();
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        System.out.println("Ganancias por depositos en dolares: " + formatoDeNumeroDecimal.format((comisionesDepositos * valorDeCompraDelDolar)));
        System.out.println("Ganancias por retiros en dolares: " + formatoDeNumeroDecimal.format((comisionesRetiros * valorDeCompraDelDolar)));
        System.out.println("Ganancias por depositos y retiros en dolares: " + formatoDeNumeroDecimal.format((comisionesDepositosYRetiros * valorDeCompraDelDolar)));
    }
}
