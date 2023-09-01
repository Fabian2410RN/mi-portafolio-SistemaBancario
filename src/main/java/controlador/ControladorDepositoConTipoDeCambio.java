/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clasesUtilitarias.Conversion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import validacion.ValidacionCuenta;
import vistaGUI.DepositoConTipoDeCambio;
import serviciosExternos.TipoCambioBCCR;
import java.time.LocalDate;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;
import singletonClasesUtilitarias.ConversionSingleton;
import vistaGUI.SeleccionDeDeposito;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorDepositoConTipoDeCambio implements ActionListener{
    public DepositoConTipoDeCambio vistaGUI;
    
    public ControladorDepositoConTipoDeCambio(DepositoConTipoDeCambio pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnAceptar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Aceptar")) {
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaDepositarDolares.getText();
            String montoDeDepositoEnDolares = this.vistaGUI.txtMontoDolares.getText();
            depositarDolaresACuenta(numeroDeCuenta, montoDeDepositoEnDolares);
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Depósito en dólares", "GUI");
            
            this.vistaGUI.txtNumeroCuentaDepositarDolares.setText("");
            this.vistaGUI.txtMontoDolares.setText("");
            ControladorMenuPrincipal.volverMenuPrincipal();
        }
        
        if(evento.getActionCommand().equals("Cancelar")) {
            SeleccionDeDeposito vistaSeleccionDeDeposito = new SeleccionDeDeposito();
            ControladorSeleccionDeDeposito controladorSeleccionDeDeposito = new ControladorSeleccionDeDeposito(vistaSeleccionDeDeposito);
            controladorSeleccionDeDeposito.vistaGUI.setVisible(true);
            controladorSeleccionDeDeposito.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
    }
    
    public static boolean depositarDolaresACuenta(String pNumeroDeCuenta, String pMontoDeDeposito){
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
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeDepositoIncorrecto();
            }
        return false;
    }
    
    public static void efectuarDeposito(String pNumeroDeCuenta, String pMontoDeDepositoEnDolares) {
        TipoCambioBCCR tipoDeCambioDeDolar = new TipoCambioBCCR();
        double tipoDeCambioDeDolarCompra = tipoDeCambioDeDolar.obtenerValorCompra();
        Conversion convertidorDeDatos = ConversionSingleton.instanciar();
        double montoDeDepositoEnColonesEnFormatoDecimal = (convertidorDeDatos.convertirStringEnDecimal(pMontoDeDepositoEnDolares)) * tipoDeCambioDeDolarCompra;
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(pNumeroDeCuenta);
        cuenta.depositar(montoDeDepositoEnColonesEnFormatoDecimal);
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(pNumeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += montoDeDepositoEnColonesEnFormatoDecimal * 0.02;
        }
        
        MensajeEnPantallaCuenta.imprimirMensajeDepositoEnDolaresExitoso(pNumeroDeCuenta, convertidorDeDatos.convertirStringEnDecimal(pMontoDeDepositoEnDolares), montoDeDepositoEnColonesEnFormatoDecimal, tipoDeCambioDeDolarCompra, montoComision, LocalDate.now());
    }
}
