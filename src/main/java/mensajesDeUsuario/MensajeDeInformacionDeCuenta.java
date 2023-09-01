/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajesDeUsuario;

/**
 *
 * @author Jairo Calderón
 */
public class MensajeDeInformacionDeCuenta {
    public String imprimirMensajeNotificacionDeEnvioDeMensaje() {
        String mensajeNotificacionDeEnvioDeMensaje = "";
        mensajeNotificacionDeEnvioDeMensaje += "Estimado usuario se ha enviado una palabra por mensaje de texto. \n";
        mensajeNotificacionDeEnvioDeMensaje += "Por favor revise sus mensajes y proceda a digitar la palabra enviada";
        return mensajeNotificacionDeEnvioDeMensaje; 
    }
    
    public String imprimirMensajeAdvertenciaSegundoIntentoPalabraSecreta() {
        String mensajeAdvertenciaSegundoIntentoPalabraSecreta = "ATENCIÓN: se enviará nuevamente la palabra secreta por mensaje de texto \n";
        mensajeAdvertenciaSegundoIntentoPalabraSecreta += "Si vuelve a ingresar una palabra incorrecta su cuenat será inactivada";
        return mensajeAdvertenciaSegundoIntentoPalabraSecreta;
    }
    
    public String imprimirMensajeAlertaDeInactivacionDeCuenta() {
        String mensajeAlertaDeInactivacionDeCuenta = "ATENCIÓN: su cuenta ha sido inactivada debido a cuestiones de seguridad \n";
        mensajeAlertaDeInactivacionDeCuenta += "Se ha enviado un correo electrónico con detalles de la inactivación";
        return mensajeAlertaDeInactivacionDeCuenta;
    }
    
    public String imprimirMensajeEstatusDeCuenta(String pNumeroDeCuenta, String pEstatusCuenta) {
        String mensajeEstatusDeCuenta = "La cuenta número "+ pNumeroDeCuenta +" tiene estatus de " + pEstatusCuenta;
        return mensajeEstatusDeCuenta;
    }
    
    public String imprimirMensajeSaldoCuentaActualColones(String pSaldoActual) {
        String mensajeSaldoCuentaActualColones = "Estimado usuario el saldo actual de su cuenta es " + pSaldoActual + " colones";
        return mensajeSaldoCuentaActualColones;
    }
    
    public String imprimirMensajeSaldoCuentaActualDolares(double pSaldoActual, double pValorDeTipoDeCambioDeCompra) {
        String mensajeSaldoCuentaActualColones = "Estimado usuario el saldo actual de su cuenta es " + String.format("%.2f", pSaldoActual) + " dólares\n" + "Para esta conversión se utilizó el tipo de cambio del dólar, precio de compra. \n" + "Según el BCCR, el tipo de cambio de compra del dólar de hoy es: "  + String.format("%.2f", pValorDeTipoDeCambioDeCompra);
        return mensajeSaldoCuentaActualColones;
    }
}
