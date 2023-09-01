/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import clasesUtilitarias.Ordenamiento;
import java.text.DecimalFormat;
import java.time.LocalDate;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import mensajesDeUsuario.MensajeDeErrorDeCuenta;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonClasesUtilitarias.OrdenamientoSingleton;
import singletonMensajesDeUsuario.ErrorDeCuentaSingleton;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeDatosDeUnaCuentaCLI {
    public ConsultaDeDatosDeUnaCuentaCLI() throws Exception {
        cargarCuentasRegistradas();
    }
    
    private void cargarCuentasRegistradas() throws Exception {
        IDAOCatalogoDeCuentas daoCatalogoDeCuentas = new DAOCatalogoDeCuentas();
        Lista<ICuenta> listaDeCuentasDesordenada = daoCatalogoDeCuentas.consultarListaDeCuentas();
        int cantidadDeCuentas = daoCatalogoDeCuentas.consultarCantidadCuentas();
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        Cuenta[] listaDeCuentasOrdenada = convertidorDeDatos.convertirListaCuentaEnArreglo(listaDeCuentasDesordenada, cantidadDeCuentas);
        Ordenamiento ordenamientoDeCuentas = OrdenamientoSingleton.instanciar();
        ordenamientoDeCuentas.ordenarDescendentemente(listaDeCuentasOrdenada);
        System.out.println("Lista de cuentas registradas en el sistema:");
        for (int i = 0; i < cantidadDeCuentas; i++) {
            Cuenta cuenta = listaDeCuentasOrdenada[i];
            String numeroDeCuenta = cuenta.numeroCuenta;
            String estatus = cuenta.estatus;
            double saldo = cuenta.getSaldo();
            int numeroPorImprimir = i + 1;
            System.out.println("\nCuenta n°" + numeroPorImprimir + ":");
            System.out.println("Número de cuenta: " + numeroDeCuenta);
            System.out.println("Estatus: " + estatus);
            DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
            System.out.println("Saldo: " + formatoDeNumeroDecimal.format(saldo));
            Cliente duenoDeCuenta = (Cliente) cuenta.propietario;
            System.out.println("Identificacion del dueño de la cuenta: " + duenoDeCuenta.identificacion);
            System.out.println("Nombre del dueño de la cuenta: " + duenoDeCuenta.nombre + " " + duenoDeCuenta.primerApellido + " " + duenoDeCuenta.segundoApellido);
        }
        System.out.println("\nDigite el número de cuenta de la cuenta sobre la cual desea conocer los detalles:");
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Consulta de datos de una cuenta", "CLI");
        this.recibirNumeroDeCuenta();
    }
    
    private void recibirNumeroDeCuenta() throws Exception {
        try {
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean identificacionIngresadaEsCorrecta = validarDatos(numeroDeCuenta);
            if(identificacionIngresadaEsCorrecta) {
                this.mostrarDetallesDeCuenta(numeroDeCuenta);
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.cargarCuentasRegistradas();
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
                this.cargarCuentasRegistradas();
            }
        }
    }
    
    private boolean validarDatos(String pNumeroDeCuenta) {
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(pNumeroDeCuenta);
        MensajeDeErrorDeCuenta mensajeDeError = ErrorDeCuentaSingleton.instanciar();
        if(existeCuenta == true) {
            return true;
        }
        else {
            System.out.println(mensajeDeError.imprimirMensajeCuentaNoExiste(pNumeroDeCuenta));
            return false;
        }
    }
    
    private void mostrarDetallesDeCuenta(String pNumeroDeCuenta) {
        IDAOCuentaIndividual daoCuentaIndividual = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuentaIndividual.consultarCuenta(pNumeroDeCuenta);
        Cliente clienteAsociadoACuenta = (Cliente) cuenta.propietario;
        String nombreCompletoDeClienteAsociadoACuenta = clienteAsociadoACuenta.nombre + " " + clienteAsociadoACuenta.primerApellido + " " + clienteAsociadoACuenta.segundoApellido;
        System.out.println("Información de la cuenta: ");
        System.out.println("Número de la cuenta: " + cuenta.numeroCuenta);
        System.out.println("Fecha de creación: " + cuenta.fechaCreacion.toString());
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        System.out.println("Saldo actual: " + formatoDeNumeroDecimal.format(cuenta.getSaldo()));
        System.out.println("Pin: " + cuenta.getPin());
        System.out.println("Nombre del propietario de la cuenta: " + nombreCompletoDeClienteAsociadoACuenta);
    }
}
