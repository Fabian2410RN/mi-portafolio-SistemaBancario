/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.text.DecimalFormat;
import java.time.LocalDate;
import listaDinamica.Lista;
import listaDinamica.Nodo;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.Operacion;
import logicaDeNegocios.RegistroGeneralBitacoras;
import mensajesDeUsuario.MensajeDeErrorDeCuenta;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonMensajesDeUsuario.ErrorDeCuentaSingleton;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeEstadoDeCuentaCLI {
    public ConsultaDeEstadoDeCuentaCLI() throws Exception {
        recibirDatos();
    }
    
    private void recibirDatos() throws Exception {
        try {
            System.out.println("Ingrese su número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese el pin de su cuenta");
            String pin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonValidos = this.validarDatos(numeroDeCuenta, pin);
            if(datosIngresadosSonValidos) {
                this.mostrarEstadoDeCuenta(numeroDeCuenta);
                RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                accion.registrarEnBitacoras(LocalDate.now(), "Consulta de estado de cuenta", "CLI");
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
    
    private void mostrarEstadoDeCuenta(String pNumeroDeCuenta) {
        IDAOCuentaIndividual cuenta = new DAOCuentaIndividual();
        Cuenta cuentaConsultada = (Cuenta) cuenta.consultarCuenta(pNumeroDeCuenta);
        System.out.println("Información de la cuenta: ");
        System.out.println("Número de cuenta: " + cuentaConsultada.numeroCuenta);
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        System.out.println("Saldo: " + formatoDeNumeroDecimal.format(cuentaConsultada.getSaldo()));
        System.out.println("\nInformación del cliente asociado a la cuenta: ");
        Cliente clientePropietario = (Cliente) cuentaConsultada.propietario;
        this.mostrarDetallesDeClienteAsociado(clientePropietario);
        IDAOOperacionCuenta operacion = new DAOOperacionCuenta();
        Lista<Operacion> operaciones = operacion.consultarOperacionesCuenta(pNumeroDeCuenta);
        System.out.println("\nInformación de las operaciones realizadas por la cuenta: \n");
        this.mostrarDetallesDeOperacionesRealizadas(operaciones);
        
    }
    
    private void mostrarDetallesDeClienteAsociado(Cliente pClienteAsociado) {
        System.out.println("Nombre completo: " + pClienteAsociado.nombre + pClienteAsociado.primerApellido + pClienteAsociado.segundoApellido);
        System.out.println("Correo electrónico: " + pClienteAsociado.correoElectronico);
        System.out.println("Número de teléfono: " + pClienteAsociado.numeroTelefono);
    }
    
    private void mostrarDetallesDeOperacionesRealizadas(Lista<Operacion> pOperaciones) {
        Nodo puntero = pOperaciones.inicio;
        while(puntero != null) {
            Operacion operacion = (Operacion)puntero.objeto;
            System.out.println("Tipo de operación: " + operacion.tipoOperacion);
            System.out.println("Fecha: " + operacion.fechaOperacion.toString());
            String aplicaComision;
            if (operacion.seAplicoComision == true){
                aplicaComision = "Sí";
            }else{
                aplicaComision = "No";
            }
            System.out.println("¿Se aplicó comisión? " + aplicaComision);
            System.out.println("Monto de comisión: " + operacion.montoComision);
            puntero = puntero.siguiente;
        }
    }
}
