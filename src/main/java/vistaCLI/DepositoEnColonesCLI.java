/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import mensajesDeUsuario.MensajeDeErrorDeCuenta;
import mensajesDeUsuario.MensajeDeMovimientoDeCuentaExitoso;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonMensajesDeUsuario.ErrorDeCuentaSingleton;
import singletonMensajesDeUsuario.MovimientoDeCuentaExitosoSingleton;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class DepositoEnColonesCLI {
    public DepositoEnColonesCLI() throws Exception {
        recibirDatos();
    }
    
    private void recibirDatos() throws Exception {
        try {
            System.out.println("Ingrese el número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese el monto del depósito");
            String montoDeDeposito = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonValidos = this.validarNumeroDeCuenta(numeroDeCuenta) && this.validarMontoDeDeposito(numeroDeCuenta, montoDeDeposito);
            if(datosIngresadosSonValidos) {
                RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                accion.registrarEnBitacoras(LocalDate.now(), "Depósito en colones", "CLI");
                this.efectuarDeposito(numeroDeCuenta, montoDeDeposito);
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirDatos();
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
                this.recibirDatos();
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
    
    private boolean validarMontoDeDeposito(String pNumeroDeCuenta, String pMontoDeDeposito) {
        boolean montoDeDepositoEsValido = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(pMontoDeDeposito);
        MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
        if(montoDeDepositoEsValido) {
            Conversion convertidorDeDatos = ConversionSingleton.instanciar();
            double montoDeDeposito = convertidorDeDatos.convertirStringEnDecimal(pMontoDeDeposito);
            boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSuficientes(pNumeroDeCuenta, montoDeDeposito);
            if(hayFondosSuficientes) {
                return true;
            }
            else {
                System.out.println(mensajeDeError.imprimirMensajeFondosInsuficientes());
                return false;
            }
        }
        else {
            System.out.println(mensajeDeError.imprimirMensajeFormatoDeMontoDeRetiroODepositoIncorrecto());
            return false;
        }
    }
    
    private void efectuarDeposito(String numeroDeCuenta, String montoDeDeposito) {
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        int montoDeDepositoEnFormatoEntero = convertidorDeDatos.convertirStringEnEntero(montoDeDeposito);
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuenta);
        cuenta.depositar(montoDeDepositoEnFormatoEntero);
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(numeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += montoDeDepositoEnFormatoEntero * 0.02;
        }
        MensajeDeMovimientoDeCuentaExitoso mensajeDeExito = MovimientoDeCuentaExitosoSingleton.instanciar();
        System.out.println(mensajeDeExito.imprimirMensajeDepositoEnColonesExitoso(numeroDeCuenta, montoDeDepositoEnFormatoEntero, montoComision));
    }
}
