/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import mensajesDeUsuario.MensajeDeErrorDeCuenta;
import mensajesDeUsuario.MensajeDeInformacionDeCuenta;
import mensajesDeUsuario.MensajeDeMovimientoDeCuentaExitoso;
import singletonMensajesDeUsuario.ErrorDeCuentaSingleton;
import singletonMensajesDeUsuario.InformacionDeCuentaSingleton;
import singletonMensajesDeUsuario.MovimientoDeCuentaExitosoSingleton;

/**
 *
 * @author estadm
 */
public class MensajeEnPantallaCuenta {
    private static MensajeDeMovimientoDeCuentaExitoso mensajeDeExito = MovimientoDeCuentaExitosoSingleton.instanciar();
    private static MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
    private static MensajeDeInformacionDeCuenta mensajeDeInformacion = InformacionDeCuentaSingleton.instanciar();
    
    public static void imprimirMensajeRegistroExitoso(String pNumeroCuenta, String pEstatus, String pSaldoActual, String pNombrePropietario, String pPrimerApellido, String pSegundoApellido, int pTelefonoAsociadoCliente, String pCorreoAsociadoCliente) {
        String mensajeRegistroExitoso = mensajeDeExito.imprimirMensajeRegistroExitoso(pNumeroCuenta, pEstatus, pSaldoActual, pNombrePropietario, pPrimerApellido, pSegundoApellido, pTelefonoAsociadoCliente, pCorreoAsociadoCliente);
        JOptionPane.showMessageDialog(null, mensajeRegistroExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeCambioDePinExitoso(String pNumeroDeCuenta) {
        String mensajeCambioDePinExitoso = mensajeDeExito.imprimirMensajeCambioDePinExitoso(pNumeroDeCuenta);
        JOptionPane.showMessageDialog(null, mensajeCambioDePinExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeDepositoEnColonesExitoso(String pNumeroDeCuenta, int pMontoDepositado, double pMontoComision) {
        String mensajeDepositoExitoso = mensajeDeExito.imprimirMensajeDepositoEnColonesExitoso(pNumeroDeCuenta, pMontoDepositado, pMontoComision);
        JOptionPane.showMessageDialog(null, mensajeDepositoExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeDepositoEnDolaresExitoso(String pNumeroDeCuenta,double pMontoDepositadoDolares, double pMontoDepositadoColones, double pTipoCambio, double pMontoComision, LocalDate pFechaDeposito) {
        String mensajeDepositoExitoso = mensajeDeExito.imprimirMensajeDepositoEnDolaresExitoso(pNumeroDeCuenta, pMontoDepositadoDolares, pMontoDepositadoColones, pTipoCambio, pMontoComision, pFechaDeposito);
        JOptionPane.showMessageDialog(null, mensajeDepositoExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeRetiroEnColonesExitoso(double pMontoRetirado, double pMontoComision) {
        String mensajeRetiroExitoso = mensajeDeExito.imprimirMensajeRetiroEnColonesExitoso(pMontoRetirado, pMontoComision);
        JOptionPane.showMessageDialog(null, mensajeRetiroExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeRetiroEnDolaresExitoso(double pMontoRetiradoColones, double pMontoRetiradoDolares, double pTipoCambio, double pMontoComision) {
        String mensajeRetiroExitoso = mensajeDeExito.imprimirMensajeRetiroEnDolaresExitoso(pMontoRetiradoColones, pMontoRetiradoDolares, pTipoCambio, pMontoComision);
        JOptionPane.showMessageDialog(null, mensajeRetiroExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeTransferenciaExitosa(double pMontoTransferido, double pMontoComision) {
        String mensajeTransferenciaExitosa = mensajeDeExito.imprimirMensajeTransferenciaExitosa(pMontoTransferido, pMontoComision);
        JOptionPane.showMessageDialog(null, mensajeTransferenciaExitosa, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeNotificacionDeEnvioDeMensaje() {
        String mensajeNotificacionDeEnvioDeMensaje = mensajeDeInformacion.imprimirMensajeNotificacionDeEnvioDeMensaje();
        JOptionPane.showMessageDialog(null, mensajeNotificacionDeEnvioDeMensaje, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeAdvertenciaSegundoIntentoPalabraSecreta() {
        String mensajeAdvertenciaSegundoIntentoPalabraSecreta = mensajeDeInformacion.imprimirMensajeAdvertenciaSegundoIntentoPalabraSecreta();
        JOptionPane.showMessageDialog(null, mensajeAdvertenciaSegundoIntentoPalabraSecreta, "Notificación", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void imprimirMensajeAlertaDeInactivacionDeCuenta() {
        String mensajeAlertaDeInactivacionDeCuenta = mensajeDeInformacion.imprimirMensajeAlertaDeInactivacionDeCuenta();
        JOptionPane.showMessageDialog(null, mensajeAlertaDeInactivacionDeCuenta, "Notificación", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorCuentaNoExiste(String pNumeroDeCuenta) {
        JOptionPane.showMessageDialog(null, mensajeDeError.imprimirMensajeCuentaNoExiste(pNumeroDeCuenta), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(String pNumeroDeCuenta, String pPin) {
        JOptionPane.showMessageDialog(null, mensajeDeError.imprimirMensajePinNoCorrespondeACuenta(pNumeroDeCuenta, pPin), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorFormatoDePinInvalido() {
        JOptionPane.showMessageDialog(null, mensajeDeError.imprimirMensajeFormatoDePinInvalido(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto() {
        JOptionPane.showMessageDialog(null, mensajeDeError.imprimirMensajeFormatoDeMontoDeRetiroODepositoIncorrecto(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorFormatoDeMontoDeDepositoIncorrecto() {
        JOptionPane.showMessageDialog(null, mensajeDeError.imprimirMensajeFormatoDeMontoDeRetiroODepositoIncorrecto(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorFondosInsuficientes() {
        JOptionPane.showMessageDialog(null, mensajeDeError.imprimirMensajeFondosInsuficientes(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorCuentaInactiva() {
        JOptionPane.showMessageDialog(null, mensajeDeError.imprimirMensajeCuentaInactiva(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeEstatusDeCuenta(String pNumeroDeCuenta, String pEstatusCuenta) {
        String mensajeCambioDePinExitoso = mensajeDeInformacion.imprimirMensajeEstatusDeCuenta(pNumeroDeCuenta, pEstatusCuenta);
        JOptionPane.showMessageDialog(null, mensajeCambioDePinExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeSaldoCuentaActualColones(double pSaldoActual) {
        String mensajeCambioDePinExitoso = mensajeDeInformacion.imprimirMensajeSaldoCuentaActualColones(String.valueOf(pSaldoActual));
        JOptionPane.showMessageDialog(null, mensajeCambioDePinExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeSaldoCuentaActualDolares(double pSaldoActual, double pValorDeCompra) {
        String mensajeCambioDePinExitoso = mensajeDeInformacion.imprimirMensajeSaldoCuentaActualDolares(pSaldoActual, pValorDeCompra);
        JOptionPane.showMessageDialog(null, mensajeCambioDePinExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorSaldoNoEsEntero() {
        String mensajeDeError = "El saldo de la cuenta no puede ir con decimales.";
        JOptionPane.showMessageDialog(null, mensajeDeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
