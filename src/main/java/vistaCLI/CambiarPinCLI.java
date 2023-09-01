/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.RegistroGeneralBitacoras;
import mensajesDeUsuario.MensajeDeErrorDeCuenta;
import mensajesDeUsuario.MensajeDeMovimientoDeCuentaExitoso;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonMensajesDeUsuario.ErrorDeCuentaSingleton;
import singletonMensajesDeUsuario.MovimientoDeCuentaExitosoSingleton;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class CambiarPinCLI {
    public CambiarPinCLI() throws Exception {
        recibirNumeroDeCuenta();
    }
    
    private void recibirNumeroDeCuenta() throws Exception {
        try {
            System.out.println("Ingrese su número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean numeroDeCuentaEsValido = this.validarNumeroDeCuenta(numeroDeCuenta);
            if(numeroDeCuentaEsValido) {
                this.recibirPinActual(numeroDeCuenta);
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
    
    private void recibirPinActual(String pNumeroDeCuenta) throws Exception {
        try {
            System.out.println("Ingrese el pin actual de su cuenta");
            String pin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean pinIngresadoEsCorrecto = this.validarPinCorrespondeACuenta(pNumeroDeCuenta, pin);
            if(pinIngresadoEsCorrecto) {
                this.recibirNuevoPin(pNumeroDeCuenta);
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirPinActual(pNumeroDeCuenta);
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
                this.recibirPinActual(pNumeroDeCuenta);
            }
        }
    }
    
    private boolean validarPinCorrespondeACuenta(String pNumeroDeCuenta, String pPin) {
        boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(pNumeroDeCuenta, pPin);
        MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
        if(pinCorrespondeACuenta) {
            return true;
        }
        else {
            System.out.println(mensajeDeError.imprimirMensajePinNoCorrespondeACuenta(pNumeroDeCuenta, pPin));
            return false;
        }
    }
    
    private void recibirNuevoPin(String pNumeroDeCuenta) throws Exception {
        MensajeDeMovimientoDeCuentaExitoso mensajeDeExito = MovimientoDeCuentaExitosoSingleton.instanciar();
        try {
            System.out.println("Ingrese el nuevo pin de su cuenta");
            String nuevoPin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean formatoDePinEsCorrecto = this.validarFormatoDePin(nuevoPin);
            if(formatoDePinEsCorrecto) {
                IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
                daoCuenta.cambiarPin(pNumeroDeCuenta, nuevoPin);
                System.out.println(mensajeDeExito.imprimirMensajeCambioDePinExitoso(pNumeroDeCuenta));
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                accion.registrarEnBitacoras(LocalDate.now(), "Cambiar pin", "CLI");
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirNuevoPin(pNumeroDeCuenta);
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
                this.recibirNuevoPin(pNumeroDeCuenta);
            }
        }
    }
    
    private boolean validarFormatoDePin(String pPin) {
        boolean formatoDePinEsCorrecto = ValidacionCuenta.validarFormatoDePin(pPin);
        MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
        if(formatoDePinEsCorrecto) {
            return true;
        }
        else {
            System.out.println(mensajeDeError.imprimirMensajeFormatoDePinInvalido());
            return false;
        }
    }
}
