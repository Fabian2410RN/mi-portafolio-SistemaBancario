/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajesDeUsuario;

/**
 *
 * @author Jairo Calderón
 */
public class MensajeDeErrorDeCuenta {
    public String imprimirMensajeCuentaNoExiste(String pNumeroDeCuenta) {
        String mensajeDeError = "No existe ninguna cuenta registrada con el número: " + pNumeroDeCuenta;
        return mensajeDeError;
    }
    
    public String imprimirMensajeClienteAsociadoNoExiste() {
        String mensajeDeError = "No existe ningún cliente con esa identificacion: ";
        return mensajeDeError;
    }
    
    public String imprimirMensajePinNoCorrespondeACuenta(String pNumeroDeCuenta, String pPin) {
        String mensajeDeError = "El pin " + pPin + " no corresponde la cuenta " + pNumeroDeCuenta;
        return mensajeDeError;
    }
    
    public String imprimirMensajeFormatoDePinInvalido() {
        String mensajeDeError = "El formato del pin ingresado no es válido \n";
        mensajeDeError += "Debe ingresar al menos una letra mayúscula, un número y un caracter especial";
        return mensajeDeError;
    }
    
    public String imprimirMensajeFormatoDeMontoDeRetiroODepositoIncorrecto() {
        String mensajeDeError = "El formato del monto de retiro o deposito ingresado no es válido \n";
        mensajeDeError += "El monto debe ser un valor numérico entero positivo";
        return mensajeDeError;
    }
    
    public String imprimirMensajeFondosInsuficientes() {
        String mensajeDeError = "Usted no tiene suficientes fondos para realizar esta transacción";
        return mensajeDeError;
    }
    
    public String imprimirMensajeCuentaInactiva() {
        String mensajeDeError = "Usted no puede realizar esta operación porque su cuenta ha sido inactivada \n";
        mensajeDeError += "Únicamente podrá consultar su estatus";
        return mensajeDeError;
    }
}
