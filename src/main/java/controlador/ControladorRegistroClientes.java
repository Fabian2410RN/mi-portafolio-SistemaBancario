/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import validacion.ExpresionRegular;
import validacion.ValidacionTipoDeDato;
import vistaGUI.RegistroClientes;
import clasesUtilitarias.Conversion;
import java.time.LocalDate;
import java.util.Date;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorRegistroClientes implements ActionListener{
    public RegistroClientes vistaGUI;
    
    public ControladorRegistroClientes(RegistroClientes pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnRegistrarCliente.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Registrar")) {
            String primerApellido = this.vistaGUI.txtPrimerApellido.getText();
            String segundoApellido = this.vistaGUI.txtSegundoApellido.getText();
            String nombre = this.vistaGUI.txtNombre.getText();
            String identificacion = this.vistaGUI.txtIdentificacion.getText();
            String numeroDeTelefono = this.vistaGUI.txtNumeroDeTelefono.getText();
            String correoElectronico = this.vistaGUI.txtCorreoElectronico.getText();
            
            String formatoFecha = "dd/MM/YYYY";
            Date fecha = this.vistaGUI.fechaNacimientoCliente.getDatoFecha();
            SimpleDateFormat formateador = new SimpleDateFormat(formatoFecha);
            String fechaDeNacimiento = formateador.format(fecha);
            
            String[] split = fechaDeNacimiento.split("/");
            String diaDeFechaDeNacimiento = split[0];
            String mesDeFechaDeNacimiento = split[1];
            String anoDeFechaDeNacimiento = split[2];
            
            registrarCliente(nombre, primerApellido, segundoApellido, identificacion, diaDeFechaDeNacimiento, mesDeFechaDeNacimiento, anoDeFechaDeNacimiento, numeroDeTelefono, correoElectronico);
            
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Registrar cliente", "GUI");
            
            this.vistaGUI.txtPrimerApellido.setText("");
            this.vistaGUI.txtSegundoApellido.setText("");
            this.vistaGUI.txtNombre.setText("");
            this.vistaGUI.txtIdentificacion.setText("");
            this.vistaGUI.txtNumeroDeTelefono.setText("");
            this.vistaGUI.txtCorreoElectronico.setText("");
        }
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
        
    }
    
    public static boolean registrarCliente(String nombre, String primerApellido, String segundoApellido, String identificacion, String diaDeFechaDeNacimiento, String mesDeFechaDeNacimiento, String anoDeFechaDeNacimiento, String numeroDeTelefono, String correoElectronico) {
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        int identificacionEnFormatoEntero = convertidorDeDatos.convertirStringEnEntero(identificacion);
        int dia = convertidorDeDatos.convertirStringEnEntero(diaDeFechaDeNacimiento);
        int mes = convertidorDeDatos.convertirStringEnEntero(mesDeFechaDeNacimiento);
        int ano = convertidorDeDatos.convertirStringEnEntero(anoDeFechaDeNacimiento);
        int numeroTelefonicoEnFormatoEntero = convertidorDeDatos.convertirStringEnEntero(numeroDeTelefono);
        
        String fechaDeNacimiento = dia+"/"+mes+"/"+ano;
        if(validarTipoDeDatos(identificacion) && validarFormatoDeDatos(numeroDeTelefono, correoElectronico) && validarExistenciaCliente(identificacionEnFormatoEntero)) {
            IDAOCatalogoDeClientes cantidadDeClientes = new DAOCatalogoDeClientes();
            int numeroDeClientePorRegistrar = cantidadDeClientes.consultarCantidadDeClientes() + 1;
            String codigo = "CIF-" + numeroDeClientePorRegistrar;
            ICliente nuevoCliente = new Cliente(codigo, nombre, primerApellido, segundoApellido, identificacionEnFormatoEntero, dia, mes, ano, numeroTelefonicoEnFormatoEntero, correoElectronico);

            MensajeEnPantallaCliente.imprimirMensajeCreadoExitoso(codigo, nombre, identificacion, fechaDeNacimiento, numeroDeTelefono);
            return true;
        }
        return false;
    }
    
    public static boolean validarTipoDeDatos(String pIdentificacion) {
        if(ValidacionTipoDeDato.verificarEsEntero(pIdentificacion) == false) {
            MensajeEnPantallaCliente.imprimirErrorIdentificacionInvalida();
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean validarFormatoDeDatos(String pNumeroDeTelefono, String pCorreoElectronico) {
        if(ExpresionRegular.verificarFormatoCorreoElectronicoEsValido(pCorreoElectronico) == false) {
          MensajeEnPantallaCliente.imprimirErrorFormatoDeCorreoIncorrecto();
          return false;
        }
        else if(ExpresionRegular.verificarFormatoNumeroTelefonicoEsValido(pNumeroDeTelefono) == false) {
            MensajeEnPantallaCliente.imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto();
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean validarExistenciaCliente(int pIdentificacion){
        IDAOCatalogoDeClientes prueb = new DAOCatalogoDeClientes();
        if(prueb.consultarSiExisteCliente(pIdentificacion) == false) {
            MensajeEnPantallaCliente.imprimirErrorIdentificacionYaExistente();
            return false;
        }
        else {
            return true;
        }
    }
    
}
