/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import validacion.ValidacionCliente;
import validacion.ValidacionTipoDeDato;
import clasesUtilitarias.Conversion;
import java.time.LocalDate;
import logicaDeNegocios.RegistroGeneralBitacoras;
import mensajesDeUsuario.MensajeDeErrorDeCliente;
import mensajesDeUsuario.MensajeDeMovimientoDeClienteExitoso;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import singletonMensajesDeUsuario.ErrorDeClienteSingleton;
import singletonMensajesDeUsuario.MovimientoDeClienteExitosoSingleton;

/**
 *
 * @author Jairo Calderón
 */
public class RegistroDeClientesCLI {
    
    public RegistroDeClientesCLI() throws Exception {
        solicitarDatos();
    }
    
    private void solicitarDatos() throws Exception {
        try {
            System.out.println("Ingrese su nombre");
            String nombre = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su primer apellido");
            String primerApellido = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su segundo apellido");
            String segundoApellido = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su identificacion");
            String identificacion = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su fecha de nacimiento en formato dd/mm/aaaa");
            String fechaDeNacimiento = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su número de teléfono");
            String numeroDeTelefono = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su correo electrónico");
            String correoElectronico = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosFueronIngresadosCorrectamente = validarDatos(identificacion, fechaDeNacimiento, numeroDeTelefono, correoElectronico);
            if(datosFueronIngresadosCorrectamente) {
                Conversion convertidorDeDatos = ConversionSingleton.instanciar();
                int identificacionEnFormatoEntero = convertidorDeDatos.convertirStringEnEntero(identificacion);
                int numeroDeTelefonoEnFormatoEntero = convertidorDeDatos.convertirStringEnEntero(numeroDeTelefono);
                String[] partesDeString = fechaDeNacimiento.split("/");
                int dia = convertidorDeDatos.convertirStringEnEntero(partesDeString[0]);
                int mes = convertidorDeDatos.convertirStringEnEntero(partesDeString[1]);
                int ano = convertidorDeDatos.convertirStringEnEntero(partesDeString[2]);
                this.registrarCliente(nombre, primerApellido, segundoApellido, identificacionEnFormatoEntero, dia, mes, ano, numeroDeTelefonoEnFormatoEntero, correoElectronico);
                
                RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
                accion.registrarEnBitacoras(LocalDate.now(), "Registro de clientes", "CLI");
                
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
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
            System.out.println("Ha ocurrido un error al ingresar el texto");
            boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
            if(usuarioDeseaVolverAMenuPrincipal) {
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
            }
            else {
                this.solicitarDatos();
            }
        }
    }
    
    private boolean validarDatos(String pIdentificacion, String pFechaDeNacimiento, String pNumeroDeTelefono, String pCorreoElectronico) {
        boolean identificacionEsDeTipoEntero = ValidacionTipoDeDato.verificarEsEntero(pIdentificacion);
        MensajeDeErrorDeCliente mensajeDeError = ErrorDeClienteSingleton.instanciar();
        if(identificacionEsDeTipoEntero) {
            boolean fechaDeNacimientoEsValida = ValidacionCliente.validarFechaNacimiento(pFechaDeNacimiento);
            if(fechaDeNacimientoEsValida) {
                boolean numeroDeTelefonoEsValido = ValidacionCliente.verificarFormatoDeNumeroDeTelefono(pNumeroDeTelefono);
                if(numeroDeTelefonoEsValido) {
                    boolean correoEsValido = ValidacionCliente.verificarFormatoDeCorreoElectronico(pCorreoElectronico);
                    if(correoEsValido) {
                        IDAOCatalogoDeClientes daoClientes = new DAOCatalogoDeClientes();
                        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
                        int identificacion = convertidorDeDatos.convertirStringEnEntero(pIdentificacion);
                        boolean noExisteCliente = daoClientes.consultarSiExisteCliente(identificacion);
                        if(noExisteCliente) {
                            return true;
                        }
                        else {
                            System.out.println(mensajeDeError.imprimirErrorIdentificacionExistente());
                            return false;
                        }
                    }
                    else {
                        System.out.println(mensajeDeError.imprimirErrorFormatoDeCorreoIncorrecto());
                        return false;
                    }
                }
                else {
                    System.out.println(mensajeDeError.imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto());
                    return false;
                }
            }
            else {
                System.out.println(mensajeDeError.imprimirErrorFechaDeNacimientoIncorrecta());
                return false;
            }
        }
        else {
            System.out.println(mensajeDeError.imprimirErrorIdentificacionInvalida());
            return false;
        }
    }
    
    private void registrarCliente(String pNombre, String pPrimerApellido, String pSegundoApellido, int pIdentificacionEnFormatoEntero, int pDia, int pMes, int pAno, int pNumeroTelefonicoEnFormatoEntero, String pCorreoElectronico) {
        IDAOCatalogoDeClientes cantidadDeClientes = new DAOCatalogoDeClientes();
        int numeroDeClientePorRegistrar = cantidadDeClientes.consultarCantidadDeClientes() + 1;
        String codigo = "CIF-" + numeroDeClientePorRegistrar;
        String fecha = pDia + "/" + pMes + "/" + pAno;
        ICliente nuevoCliente = new Cliente(codigo, pNombre, pPrimerApellido, pSegundoApellido, pIdentificacionEnFormatoEntero, pDia, pMes, pAno, pNumeroTelefonicoEnFormatoEntero, pCorreoElectronico);
        MensajeDeMovimientoDeClienteExitoso mensajeDeExito = MovimientoDeClienteExitosoSingleton.instanciar();
        System.out.println(mensajeDeExito.imprimirMensajeClienteCreadoExitoso(codigo, pNombre, String.valueOf(pIdentificacionEnFormatoEntero), fecha, String.valueOf(pNumeroTelefonicoEnFormatoEntero)));
    }
}
