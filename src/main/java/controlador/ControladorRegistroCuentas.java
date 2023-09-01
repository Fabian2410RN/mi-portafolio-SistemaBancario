/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.ICuenta;
import vistaGUI.RegistroCuentasVista;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.IDAOCliente;
import clasesUtilitarias.Conversion;
import static controlador.MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDePinInvalido;
import static controlador.MensajeEnPantallaCuenta.imprimirMensajeDeErrorSaldoNoEsEntero;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
/**
 *
 * @author estadm
 */
public class ControladorRegistroCuentas implements ActionListener{
    public RegistroCuentasVista vistaGUI;
    
    public ControladorRegistroCuentas(RegistroCuentasVista pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnRegistrar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Registrar")){
            String pin = this.vistaGUI.txtPinDatosCuenta.getText();
            String montoInicial = this.vistaGUI.txtMontoInicial.getText();
            String identificacionCliente = this.vistaGUI.txtIdentificacionDatodCuenta.getText();
            
            registrarCuenta(pin, montoInicial, identificacionCliente);
            
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Registrar cuenta", "GUI");
            
            this.vistaGUI.txtPinDatosCuenta.setText("");
            this.vistaGUI.txtMontoInicial.setText("");
            this.vistaGUI.txtIdentificacionDatodCuenta.setText("");
    }
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
   
    
    public static boolean registrarCuenta(String pPin, String pMontoInicial, String pIdentificacionCliente){
        IDAOCatalogoDeClientes obtenerCliente = new DAOCatalogoDeClientes();
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        boolean validarClienteSiYaExiste = obtenerCliente.consultarSiNOExisteCliente(convertidorDeDatos.convertirStringEnEntero(pIdentificacionCliente));
        if(validacion.ValidacionCuenta.validarFormatoDePin(pPin)){
                if(validacion.ValidacionTipoDeDato.verificarEsEntero(pMontoInicial)){
                    if(validarClienteSiYaExiste){
                        try {
                            double montoInicialConvetidoDouble = convertidorDeDatos.convertirStringEnDecimal(pMontoInicial);
                            IDAOCliente DAOCliente = new DAOCliente();
                            ICliente clienteAsociadoConCuenta = DAOCliente.consultarCliente(convertidorDeDatos.convertirStringEnEntero(pIdentificacionCliente));
                            Cliente cliente = (Cliente) clienteAsociadoConCuenta;
                            String nombreCliente = cliente.nombre;
                            String primerApellido = cliente.primerApellido;
                            String segundoApellido = cliente.segundoApellido;
                            int telefonoCliente = cliente.numeroTelefono;
                            String correoElectronicoCliente = cliente.correoElectronico;
                            IDAOCatalogoDeCuentas cantidadCuentas = new DAOCatalogoDeCuentas();
                            String estatusCuenta = "Activa";
                            int obtenerCantidadCuentas = cantidadCuentas.consultarCantidadCuentas()+1;
                            String numeroCuenta = "CU-" + obtenerCantidadCuentas;
                            ICuenta nuevaCuenta = null;
                            nuevaCuenta = new Cuenta(numeroCuenta, 0, estatusCuenta, pPin);
                            nuevaCuenta.asignarPropietario(clienteAsociadoConCuenta);
                            nuevaCuenta.depositar(montoInicialConvetidoDouble);
                            clienteAsociadoConCuenta.asignarCuenta(nuevaCuenta);
                            MensajeEnPantallaCuenta.imprimirMensajeRegistroExitoso(numeroCuenta, estatusCuenta, String.format("%.2f",montoInicialConvetidoDouble)+" ₡", nombreCliente, primerApellido, segundoApellido, telefonoCliente, correoElectronicoCliente);
                            return true;
                        } 
                        catch (Exception ex) {

                        }
                    }else{
                        controlador.MensajeEnPantallaCliente.imprimirErrorIdentificacionExistente();
                    }
            }else{
                imprimirMensajeDeErrorSaldoNoEsEntero();
                }
            }else{
                imprimirMensajeDeErrorFormatoDePinInvalido();
        }
        
            
        return false;
        }
}
