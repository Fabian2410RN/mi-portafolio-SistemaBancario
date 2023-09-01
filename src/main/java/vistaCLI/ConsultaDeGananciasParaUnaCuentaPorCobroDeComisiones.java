/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.text.DecimalFormat;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import mensajesDeUsuario.MensajeDeErrorDeCuenta;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonMensajesDeUsuario.ErrorDeCuentaSingleton;
import validacion.ValidacionCuenta;
import vistaGUI.MenuPrincipal;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeGananciasParaUnaCuentaPorCobroDeComisiones {
    public ConsultaDeGananciasParaUnaCuentaPorCobroDeComisiones() throws Exception {
        this.recibirNumeroDeCuenta();
    }
    
    private void recibirNumeroDeCuenta() throws Exception {
        try {
            System.out.println("Ingrese su número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean numeroDeCuentaEsValido = this.validarNumeroDeCuenta(numeroDeCuenta);
            if(numeroDeCuentaEsValido) {
                RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                accion.registrarEnBitacoras(LocalDate.now(), "Ganancia por comisión para una cuenta", "CLI");
                this.mostrarGananciasTotalesDeBancoPorCobroDeComisiones(numeroDeCuenta);
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirNumeroDeCuenta();
                }
            }
        } 
        catch (Exception ex) {
            System.out.println("Ha ocurrido un error al recibir el texto");
            boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
            if(usuarioDeseaVolverAMenuPrincipal) {
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
            }
            else {
                this.recibirNumeroDeCuenta();
            }
        }   
    }
    
    private boolean validarNumeroDeCuenta(String pNumeroDeCuenta) {
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(pNumeroDeCuenta);
        MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
        if(existeCuenta) {
            return true;
        }
        else {
            System.out.println(mensajeDeError.imprimirMensajeCuentaNoExiste(pNumeroDeCuenta));
            return false;
        }
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisiones(String pNumeroCuenta) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(pNumeroCuenta);
        System.out.println("\nGanancias del Banco por el cobro de comisiones a la cuenta: " + pNumeroCuenta);
        System.out.println("\nResultados en colones: ");
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones(pNumeroCuenta);
        System.out.println("\nResultados en dólares: ");
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares(pNumeroCuenta);
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
