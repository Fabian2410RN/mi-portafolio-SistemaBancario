/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import clasesUtilitarias.Ordenamiento;
import java.time.LocalDate;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.ICuenta;
import validacion.ValidacionCliente;
import validacion.ValidacionTipoDeDato;
import listaDinamica.Nodo;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import mensajesDeUsuario.MensajeDeErrorDeCliente;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonClasesUtilitarias.OrdenamientoSingleton;
import singletonMensajesDeUsuario.ErrorDeClienteSingleton;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeDatosDeUnClienteCLI {
    public ConsultaDeDatosDeUnClienteCLI() throws Exception {
        cargarClientesRegistrados();
    }
    
    private void cargarClientesRegistrados() throws Exception {
        Cliente[] arregloClientesOrdenados;
        IDAOCatalogoDeClientes daoCatalogoDeClientes = new DAOCatalogoDeClientes();
        int cantidadDeClientes = daoCatalogoDeClientes.consultarCantidadDeClientes();
        Lista<ICliente> consultarListaCliente = daoCatalogoDeClientes.consultarListaDeClientes();
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        arregloClientesOrdenados = convertidorDeDatos.convertirListaClienteEnArreglo(consultarListaCliente, cantidadDeClientes);
        Ordenamiento ordenamientoDeClientes = OrdenamientoSingleton.instanciar();
        arregloClientesOrdenados = ordenamientoDeClientes.ordenarAscendentemente(arregloClientesOrdenados);
        System.out.println("Lista de clientes registrados en el sistema:");
        for (int i = 0; i < cantidadDeClientes; i++) {
            String primerApellido = arregloClientesOrdenados[i].primerApellido;
            String segundoApellido = arregloClientesOrdenados[i].segundoApellido;
            String nombre = arregloClientesOrdenados[i].nombre;
            int identificacion = arregloClientesOrdenados[i].identificacion;
            int imprimirNumero = i+1;
            System.out.println("\nCliente n°" + imprimirNumero + ":");
            System.out.println("Nombre completo: " + nombre + " " + primerApellido + " " + segundoApellido);
            System.out.println("Identificación: " + identificacion);
        }
        System.out.println("\nDigite la identificación del cliente sobre el cual desea conocer los detalles:");
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Consulta de datos de un cliente", "CLI");
        this.recibirIdentificacionDeCliente();
    }
    
    private void recibirIdentificacionDeCliente() throws Exception {
        String identificacion = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
        boolean identificacionIngresadaEsCorrecta = validarDatos(identificacion);
        if(identificacionIngresadaEsCorrecta) {
            this.mostrarDetallesDeCliente(identificacion);
            MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
        }
        else {
            boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
            if(usuarioDeseaVolverAMenuPrincipal) {
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
            }
            else {
                this.cargarClientesRegistrados();
            }
        }
    }
    
    private boolean validarDatos(String pIdentificacion) {
        boolean formatoDeIdentificacionEsCorrecto = ValidacionTipoDeDato.verificarEsEntero(pIdentificacion);
        MensajeDeErrorDeCliente mensajeDeError = ErrorDeClienteSingleton.instanciar();
        if(formatoDeIdentificacionEsCorrecto) {
            Conversion convertidorDeDatos = ConversionSingleton.instanciar();
            boolean noExisteCliente = ValidacionCliente.existeCliente(convertidorDeDatos.convertirStringEnEntero(pIdentificacion));
            if(noExisteCliente == false) {
                return true;
            }
            else {
                System.out.println(mensajeDeError.imprimirErrorIdentificacionInexistente());
                return false;
            }
        }
        else {
            System.out.println(mensajeDeError.imprimirErrorIdentificacionInvalida());
            return false;
        }
    }
    
    private void mostrarDetallesDeCliente(String pIdentificacionDeCliente) throws Exception {
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        int identificacionDeCliente = convertidorDeDatos.convertirStringEnEntero(pIdentificacionDeCliente);
        IDAOCliente daoCliente = new DAOCliente();
        Cliente cliente = (Cliente) daoCliente.consultarCliente(identificacionDeCliente);
        System.out.println("Información del cliente: ");
        System.out.println("Código: " + cliente.getCodigo());
        System.out.println("Identificación: " + cliente.identificacion);
        System.out.println("Nombre completo: " + cliente.nombre + " " + cliente.primerApellido + " " + cliente.segundoApellido);
        System.out.println("Fecha de nacimiento: " + cliente.getFechaNacimiento());
        System.out.println("Correo electrónico: " + cliente.correoElectronico);
        System.out.println("Número de teléfono: " + cliente.numeroTelefono);
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        Lista<ICuenta> listaDeCuentasDeCliente = daoClienteCuenta.consultarCuentasDeCliente(identificacionDeCliente);
        Nodo puntero = listaDeCuentasDeCliente.inicio;
        System.out.println("Cuentas asignadas al cliente: \n");
        while(puntero != null) {
            Cuenta cuenta = (Cuenta) puntero.objeto;
            System.out.println("Número de cuenta: " + cuenta.numeroCuenta);
            puntero = puntero.siguiente;
        }
        MenuPrincipalCLI volverAMenuPrincipal = new MenuPrincipalCLI();
    }
}
