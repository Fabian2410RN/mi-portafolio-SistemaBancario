/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import clasesUtilitarias.PalabraSecreta;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import mensajesDeUsuario.MensajeDeErrorDeCuenta;
import mensajesDeUsuario.MensajeDeInformacionDeCuenta;
import mensajesDeUsuario.MensajeDeMovimientoDeCuentaExitoso;
import serviciosExternos.EnvioCorreoElectronico;
import serviciosExternos.EnvioMensajeDeTexto;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonClasesUtilitarias.PalabraSecretaSingleton;
import singletonMensajesDeUsuario.ErrorDeCuentaSingleton;
import singletonMensajesDeUsuario.InformacionDeCuentaSingleton;
import singletonMensajesDeUsuario.MovimientoDeCuentaExitosoSingleton;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class TransferenciaCLI {
    private int cantidadDeIntentosRealizados = 0;
    private final int cantidadMaximaDeIntentosPermitida = 2;
    
    public TransferenciaCLI() throws Exception {
        recibirDatosDeCuentaDeOrigen();
    }
    
    private void recibirDatosDeCuentaDeOrigen() throws Exception {
        try {
            System.out.println("Ingrese su número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese el pin de su cuenta");
            String pin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonValidos = this.validarDatos(numeroDeCuenta, pin);
            if(datosIngresadosSonValidos) {
                this.enviarMensajeDeTexto(numeroDeCuenta);
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirDatosDeCuentaDeOrigen();
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
                this.recibirDatosDeCuentaDeOrigen();
            }
        }
    }
    
    private boolean validarDatos(String pNumeroDeCuenta, String pPin) {
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(pNumeroDeCuenta);
        MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
        if(existeCuenta) {
            boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(pNumeroDeCuenta, pPin);
            if(pinCorrespondeACuenta) {
                return true;
            }
            else {
                System.out.println(mensajeDeError.imprimirMensajeFormatoDePinInvalido());
                return false;
            }
        }
        else {
            System.out.println(mensajeDeError.imprimirMensajeCuentaNoExiste(pNumeroDeCuenta));
            return false;
        }
    }
    
    private void enviarMensajeDeTexto(String pNumeroDeCuentaDeOrigen) throws Exception {
        MensajeDeInformacionDeCuenta mensajeDeInformacion = InformacionDeCuentaSingleton.instanciar();
        System.out.println(mensajeDeInformacion.imprimirMensajeNotificacionDeEnvioDeMensaje());
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        Cliente clienteAsociadoACuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(pNumeroDeCuentaDeOrigen);
        int numeroDeTelefonoDeDuenoDeLaCuentaDeOrigen = clienteAsociadoACuenta.numeroTelefono;
        EnvioMensajeDeTexto mensajeDeTexto = new EnvioMensajeDeTexto();
        PalabraSecreta generadorDePalabraSecreta = PalabraSecretaSingleton.instanciar();
        String mensajeSecreto = generadorDePalabraSecreta.generarPalabraSecreta();
        String mensaje = "Estimado usuario de la cuenta: " + pNumeroDeCuentaDeOrigen + " su palabra secreta es: \n";
        mensaje += mensajeSecreto + "\n";
        mensaje += "Ingrese esta palabra correctamente para proceder con su transferencia";
        mensajeDeTexto.enviarMensaje(String.valueOf(numeroDeTelefonoDeDuenoDeLaCuentaDeOrigen), mensaje);
        this.recibirMensajeDeTexto(pNumeroDeCuentaDeOrigen, mensajeSecreto);
    }
    
    private void recibirMensajeDeTexto(String pNumeroDeCuenta, String pMensajeDeTextoEnviado) throws Exception {
        MensajeDeInformacionDeCuenta mensajeDeInformacion = InformacionDeCuentaSingleton.instanciar();
        try {
            String mensajeIngresado = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            this.cantidadDeIntentosRealizados++;
            if(pMensajeDeTextoEnviado.equals(mensajeIngresado)) {
                this.recibirMontoDeTransferencia(pNumeroDeCuenta);
            }
            else {
                if(this.cantidadDeIntentosRealizados == this.cantidadMaximaDeIntentosPermitida) {
                    this.inactivarCuenta(pNumeroDeCuenta);
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                    if(usuarioDeseaVolverAMenuPrincipal) {
                        MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                    }
                    else {
                        this.enviarMensajeDeTexto(pNumeroDeCuenta);
                        mensajeDeInformacion.imprimirMensajeAdvertenciaSegundoIntentoPalabraSecreta();
                    }
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
                this.enviarMensajeDeTexto(pNumeroDeCuenta);
                this.cantidadDeIntentosRealizados = this.cantidadDeIntentosRealizados - 1;
            }
        }
    }
    
    private void inactivarCuenta(String pNumeroDeCuenta) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        daoCuenta.actualizarEstatus(pNumeroDeCuenta, "Inactiva");
        MensajeDeInformacionDeCuenta mensajeDeInformacion = InformacionDeCuentaSingleton.instanciar();
        System.out.println(mensajeDeInformacion.imprimirMensajeAlertaDeInactivacionDeCuenta());
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        Cliente clienteAsociadoACuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(pNumeroDeCuenta);
        String correoDestinatario = clienteAsociadoACuenta.correoElectronico;
        String mensajeDeCorreo = "";
        mensajeDeCorreo += "Estimado cliente: su cuenta " + pNumeroDeCuenta + " ha sido desactividada \n";
        mensajeDeCorreo += "La inactivación se debe a que realizó muchos intentos de validación de retiro/transferencia \n";
        String asuntoDeCorreo = "Inactivación de cuenta " + pNumeroDeCuenta;
        EnvioCorreoElectronico.enviarCorreo(correoDestinatario, asuntoDeCorreo, mensajeDeCorreo);
    }
    
    private void recibirMontoDeTransferencia(String pNumeroDeCuenta) throws Exception {
        try {
            System.out.println("Ingrese el monto que desea transferir");
            String montoDeTransferencia = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean montoDeTransferenciaEsValido = this.validarMontoDeTransferencia(pNumeroDeCuenta, montoDeTransferencia);
            if(montoDeTransferenciaEsValido) {
                Conversion convertidorDeDatos = ConversionSingleton.instanciar();
                double montoDeTransferenciaEnFormatoDecimal = convertidorDeDatos.convertirStringEnDecimal(montoDeTransferencia);
                this.recibirCuentaDeDestino(pNumeroDeCuenta, montoDeTransferenciaEnFormatoDecimal);
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirMontoDeTransferencia(pNumeroDeCuenta);
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
                this.recibirMontoDeTransferencia(pNumeroDeCuenta);
            }
        }   
    }
    
    private boolean validarMontoDeTransferencia(String pNumeroDeCuenta, String pMontoDeTransferencia) {
        boolean montoDeTransferenciaEsValido = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(pMontoDeTransferencia);
        MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
        if(montoDeTransferenciaEsValido) {
            Conversion convertidorDeDatos = ConversionSingleton.instanciar();
            double montoDeTransferencia = convertidorDeDatos.convertirStringEnDecimal(pMontoDeTransferencia);
            boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSuficientes(pNumeroDeCuenta, montoDeTransferencia);
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
    
    private void recibirCuentaDeDestino(String pNumeroDeCuenta, double pMontoTransferencia) throws Exception {
        try {
            System.out.println("Ingrese el número de cuenta de destino");
            String numeroDeCuentaDeDestino = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean numeroDeCuentaEsValido = this.validarNumeroDeCuentaDeDestino(numeroDeCuentaDeDestino);
            if(numeroDeCuentaEsValido) {
                this.efectuarTransferencia(pNumeroDeCuenta, numeroDeCuentaDeDestino, pMontoTransferencia);
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirCuentaDeDestino(pNumeroDeCuenta, pMontoTransferencia);
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
                this.recibirCuentaDeDestino(pNumeroDeCuenta, pMontoTransferencia);
            }
        }
    }
    
    private boolean validarNumeroDeCuentaDeDestino(String pNumeroDeCuentaDeDestino) {
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(pNumeroDeCuentaDeDestino);
        MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
        if(existeCuenta) {
            return true;
        }
        else {
            System.out.println(mensajeDeError.imprimirMensajeCuentaNoExiste(pNumeroDeCuentaDeDestino));
            return false;
        }
    }
    
    private double calcularMontoComision(String pNumeroDeCuenta, double pMontoPorTransferir) {
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(pNumeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += pMontoPorTransferir * 0.02;
        }
        return montoComision;
    }
    
    private void efectuarTransferencia(String numeroDeCuentaDeOrigen, String pNumeroDeCuentaDeDestino, double montoDeTransferencia) throws Exception {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuentaDeOrigen = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuentaDeOrigen);
        Cuenta cuentaDeDestino = (Cuenta) daoCuenta.consultarCuenta(pNumeroDeCuentaDeDestino);
        cuentaDeOrigen.transferir(cuentaDeDestino, montoDeTransferencia);
        double montoComision = this.calcularMontoComision(numeroDeCuentaDeOrigen, montoDeTransferencia);
        MensajeDeMovimientoDeCuentaExitoso mensajeDeExito = MovimientoDeCuentaExitosoSingleton.instanciar();
        System.out.println(mensajeDeExito.imprimirMensajeTransferenciaExitosa(montoDeTransferencia, montoComision));
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Transferencia", "CLI");
        
        MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
    }
}
