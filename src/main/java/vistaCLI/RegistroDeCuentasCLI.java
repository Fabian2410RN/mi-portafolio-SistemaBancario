/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.ICuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import mensajesDeUsuario.MensajeDeErrorDeCuenta;
import mensajesDeUsuario.MensajeDeMovimientoDeCuentaExitoso;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonMensajesDeUsuario.ErrorDeCuentaSingleton;
import singletonMensajesDeUsuario.MovimientoDeCuentaExitosoSingleton;
import validacion.ValidacionCliente;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class RegistroDeCuentasCLI {
    public RegistroDeCuentasCLI() throws Exception {
        solicitarDatos();
    }
    
    private void solicitarDatos() throws Exception {
        MensajeDeMovimientoDeCuentaExitoso mensajeDeExito = MovimientoDeCuentaExitosoSingleton.instanciar();
        try {
            System.out.println("Ingrese el pin de la cuenta");
            String pin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese el monto del depósito inicial de la cuenta");
            String depositoInicial = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su identificacion");
            String identificacionCliente = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonCorrectos = validarDatos(pin, depositoInicial, identificacionCliente);
            if(datosIngresadosSonCorrectos) {
                try {
                    Conversion convertidorDeDatos = ConversionSingleton.instanciar();
                    double montoInicialConvetidoDouble = convertidorDeDatos.convertirStringEnDecimal(depositoInicial);
                    IDAOCliente DAOCliente = new DAOCliente();
                    ICliente clienteAsociadoConCuenta = DAOCliente.consultarCliente(convertidorDeDatos.convertirStringEnEntero(identificacionCliente));
                    Cliente cliente = (Cliente) clienteAsociadoConCuenta;
                    String nombreCliente = cliente.nombre;
                    String primerApellido = cliente.primerApellido;
                    String segundoApellido = cliente.segundoApellido;
                    int telefonoCliente = cliente.numeroTelefono;
                    String correoElectronicoCliente = cliente.correoElectronico;
                    IDAOCatalogoDeCuentas cantidadCuentas = new DAOCatalogoDeCuentas();
                    String estatusCuenta = "Activa";
                    int numeroDeRegistroDeCuenta = cantidadCuentas.consultarCantidadCuentas() + 1;
                    String numeroCuenta = "CU-" + numeroDeRegistroDeCuenta;
                    ICuenta nuevaCuenta = null;
                    nuevaCuenta = new Cuenta(numeroCuenta, 0, estatusCuenta, pin);
                    nuevaCuenta.asignarPropietario(clienteAsociadoConCuenta);
                    nuevaCuenta.depositar(montoInicialConvetidoDouble);
                    clienteAsociadoConCuenta.asignarCuenta(nuevaCuenta);
                    System.out.println(mensajeDeExito.imprimirMensajeRegistroExitoso(numeroCuenta, estatusCuenta, depositoInicial, nombreCliente, primerApellido, segundoApellido, telefonoCliente, correoElectronicoCliente));
                    RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                    accion.registrarEnBitacoras(LocalDate.now(), "Registro de cuentas", "CLI");
                    MenuPrincipalCLI volverAMenuPrincipal = new MenuPrincipalCLI();
                }
                catch (Exception ex) {
                    System.out.println("Ocurrio un error al ingresar los datos");
                    boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                    if(usuarioDeseaVolverAMenuPrincipal) {
                        MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                    }
                    else {
                        this.solicitarDatos();
                    }
                }
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.solicitarDatos();
                }
            }
        } 
        catch (Exception ex) {
            System.out.println("Ocurrio un error al ingresar los datos");
            boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
            if(usuarioDeseaVolverAMenuPrincipal) {
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
            }
            else {
                this.solicitarDatos();
            }
        }
    }
    
    private boolean validarDatos(String pPin, String pDepositoInicial, String pIdentificacionCliente) {
        boolean formatoDePinEsCorrecto = ValidacionCuenta.validarFormatoDePin(pPin);
        MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
        if(formatoDePinEsCorrecto) {
            boolean formatoDeDepositoInicialEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(pDepositoInicial);
            if(formatoDeDepositoInicialEsCorrecto) {
                Conversion convertidorDeDatos = ConversionSingleton.instanciar();
                boolean noExisteCliente = ValidacionCliente.existeCliente(convertidorDeDatos.convertirStringEnEntero(pIdentificacionCliente));
                if(noExisteCliente == false) {
                    return true;
                }
                else {
                    System.out.println(mensajeDeError.imprimirMensajeClienteAsociadoNoExiste());
                    return false;
                }
            }
            else {
                System.out.println(mensajeDeError.imprimirMensajeFormatoDeMontoDeRetiroODepositoIncorrecto());
                return false;
            }
        }
        else {
            System.out.println(mensajeDeError.imprimirMensajeFormatoDePinInvalido());
            return false;
        }
    }
}
