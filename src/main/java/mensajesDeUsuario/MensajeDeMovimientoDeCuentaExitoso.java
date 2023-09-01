/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajesDeUsuario;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 *
 * @author Jairo Calderón
 */
public class MensajeDeMovimientoDeCuentaExitoso {
    public String imprimirMensajeRegistroExitoso(String pNumeroCuenta, String pEstatus, String pSaldoActual, String pNombrePropietario, String pPrimerApellido, String pSegundoApellido, int pTelefonoAsociadoCliente, String pCorreoAsociadoCliente) {
        String mensajeRegistroExitoso = "";
        mensajeRegistroExitoso += "Se ha creado una nueva cuenta en el sistema, los datos de la cuenta son: \n\n";
        mensajeRegistroExitoso += "Numero de cuenta: " + pNumeroCuenta + "\n";
        mensajeRegistroExitoso += "Estatus de la cuenta: " + pEstatus + "\n";
        mensajeRegistroExitoso += "Saldo actual: " + pSaldoActual + "\n";
        mensajeRegistroExitoso += "-- \n";
        mensajeRegistroExitoso += "Nombre del dueno de la cuenta: " + pNombrePropietario+" "+ pPrimerApellido +" "+ pSegundoApellido + "\n";
        mensajeRegistroExitoso += "Número de teléfono “asociado” a la cuenta: " + pTelefonoAsociadoCliente + "\n";
        mensajeRegistroExitoso += "Dirección de correo electrónico “asociada” a la cuenta: " + pCorreoAsociadoCliente;
        return mensajeRegistroExitoso;
    }
    
    public String imprimirMensajeCambioDePinExitoso(String pNumeroDeCuenta) {
        String mensajeCambioDePinExitoso = "Se ha modificado exitosamente el pin de la cuenta: " + pNumeroDeCuenta;
        return mensajeCambioDePinExitoso;
    }
    
    public String imprimirMensajeDepositoEnColonesExitoso(String pNumeroDeCuenta, int pMontoDepositado, double pMontoComision) {
        String mensajeDepositoExitoso = "";
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        mensajeDepositoExitoso += "Estimado usuario, se han depositado correctamente " + formatoDeNumeroDecimal.format(pMontoDepositado) + " colones \n";
        mensajeDepositoExitoso += "[El monto real depositado a su cuenta" + pNumeroDeCuenta + "es de " + formatoDeNumeroDecimal.format((pMontoDepositado - pMontoComision)) + " colones] \n";
        mensajeDepositoExitoso += "[El monto cobrado por concepto de comisión fue de " + formatoDeNumeroDecimal.format(pMontoComision) + " colones, que fueron rebajados automáticamente de su saldo actual]";
        return mensajeDepositoExitoso;
    }
    
    public String imprimirMensajeDepositoEnDolaresExitoso(String pNumeroDeCuenta,double pMontoDepositadoDolares, double pMontoDepositadoColones, double pTipoCambio, double pMontoComision, LocalDate pFechaDeposito) {
        String mensajeDepositoExitoso = "";
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        mensajeDepositoExitoso += "Estimado usuario, se han recibido correctamente " + formatoDeNumeroDecimal.format(pMontoDepositadoDolares) + " dolares \n";
        mensajeDepositoExitoso += "[Según el BCCR, el tipo de cambio de compra del dólar de " + pFechaDeposito + " es: " + formatoDeNumeroDecimal.format(pTipoCambio) + "] \n";
        mensajeDepositoExitoso += "[El monto equivalente en colones es " + formatoDeNumeroDecimal.format(pMontoDepositadoColones) + "] \n";
        mensajeDepositoExitoso += "[El monto real depositado a su cuenta " + pNumeroDeCuenta + " es de " + formatoDeNumeroDecimal.format((pMontoDepositadoColones - pMontoComision)) + " colones] \n";
        mensajeDepositoExitoso += "[El monto cobrado por concepto de comisión fue de " + pMontoComision + " colones, que fueron rebajados automáticamente de su saldo actual]";
        return mensajeDepositoExitoso;
    }
    
    public String imprimirMensajeRetiroEnColonesExitoso(double pMontoRetirado, double pMontoComision) {
        String mensajeRetiroExitoso = "";
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        mensajeRetiroExitoso += "Estimado usuario, el monto de este retiro es " + formatoDeNumeroDecimal.format(pMontoRetirado) + " colones \n";
        mensajeRetiroExitoso += "[El monto cobrado por concepto de comisión fue de " + formatoDeNumeroDecimal.format(pMontoComision) + " colones, que fueron rebajados automáticamente de su saldo actual]";
        return mensajeRetiroExitoso;
    }
    
    public String imprimirMensajeRetiroEnDolaresExitoso(double pMontoRetiradoColones, double pMontoRetiradoDolares, double pTipoCambio, double pMontoComision) {
        String mensajeRetiroExitoso = "";
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        mensajeRetiroExitoso += "Estimado usuario, el monto de este retiro es " + formatoDeNumeroDecimal.format(pMontoRetiradoDolares) + " dolares \n";
        mensajeRetiroExitoso += "[Según el BCCR, el tipo de cambio de venta del dólar es: " + formatoDeNumeroDecimal.format(pTipoCambio) + "] \n";
        mensajeRetiroExitoso += "[El monto equivalente de su retiro es es " + formatoDeNumeroDecimal.format(pMontoRetiradoColones) + "] \n";
        mensajeRetiroExitoso += "[El monto cobrado por concepto de comisión fue de " + formatoDeNumeroDecimal.format(pMontoComision) + " colones, que fueron rebajados automáticamente de su saldo actual]";
        return mensajeRetiroExitoso;
    }
    
    public String imprimirMensajeTransferenciaExitosa(double pMontoTransferido, double pMontoComision) {
        String mensajeTransferenciaExitosa = "";
        mensajeTransferenciaExitosa += "Estimado usuario, la transferencia de fondos se ejecuto satisfactoriamente. \n";
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        mensajeTransferenciaExitosa += "El monto retirado de la cuenta origen y depositado en la cuenta destino es " + formatoDeNumeroDecimal.format(pMontoTransferido) + "\n";
        mensajeTransferenciaExitosa += "[El monto cobrado por concepto de comisión a la cuenta origen fue de " + formatoDeNumeroDecimal.format(pMontoComision) + " colones, que fueron rebajados automáticamente de su saldo actual] \n";
        return mensajeTransferenciaExitosa;
    }
}
