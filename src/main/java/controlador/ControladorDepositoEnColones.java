/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clasesUtilitarias.Conversion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import validacion.ValidacionCuenta;
import vistaGUI.DepositoEnColones;
import vistaGUI.SeleccionDeDeposito;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorDepositoEnColones implements ActionListener{
    public DepositoEnColones vistaGUI;
    
    public ControladorDepositoEnColones(DepositoEnColones pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.jButton1.addActionListener(this);
        this.vistaGUI.jButton2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Aceptar")) {
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaDeposito.getText();
            String montoDeDeposito = this.vistaGUI.txtMontoDeposito.getText();
            depositarColonesACuenta(numeroDeCuenta, montoDeDeposito);
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Depósito en colones", "GUI");
            
            this.vistaGUI.txtNumeroCuentaDeposito.setText("");
            this.vistaGUI.txtMontoDeposito.setText("");
            ControladorMenuPrincipal.volverMenuPrincipal();
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Cancelar")) {
            SeleccionDeDeposito vistaSeleccionDeDeposito = new SeleccionDeDeposito();
            ControladorSeleccionDeDeposito controladorSeleccionDeDeposito = new ControladorSeleccionDeDeposito(vistaSeleccionDeDeposito);
            controladorSeleccionDeDeposito.vistaGUI.setVisible(true);
            controladorSeleccionDeDeposito.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
    }
    
    public static boolean depositarColonesACuenta(String pNumeroDeCuenta, String pMontoDeDeposito){
        boolean formatoDeMontoDeDepositoEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(pMontoDeDeposito);
            if(formatoDeMontoDeDepositoEsCorrecto) {
                boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(pNumeroDeCuenta);
                if(existeCuenta) {
                    boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(pNumeroDeCuenta);
                    if(cuentaEstaActiva) {
                        efectuarDeposito(pNumeroDeCuenta, pMontoDeDeposito);
                        
                        return true;
                    }
                    else {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                    }
                }
                else {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(pNumeroDeCuenta);
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto();
            }
            return false;
    }
    
    private static void efectuarDeposito(String numeroDeCuenta, String montoDeDeposito) {
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        int montoDeDepositoEnFormatoEntero = convertidorDeDatos.convertirStringEnEntero(montoDeDeposito);
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuenta);
        cuenta.depositar(montoDeDepositoEnFormatoEntero);
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(numeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += montoDeDepositoEnFormatoEntero * 0.02;
        }
        
        MensajeEnPantallaCuenta.imprimirMensajeDepositoEnColonesExitoso(numeroDeCuenta, montoDeDepositoEnFormatoEntero, montoComision);
    }
}
        
